package com.vzwcorp.pricinglab.helper;

import com.vzwcorp.pricinglab.service.ServiceManager;
import com.vzwcorp.pricinglab.vo.*;
import com.vzwcorp.pricinglab.vo.repository.*;
import org.apache.tomcat.util.digester.Rules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * Created by menonka on 8/24/2016.
 */
@Configuration
public class ServiceHelper {

    @Autowired
    ApplicationRepository appRepository;

    @Autowired
    TimeFrameRepository timeFrameRepository;

    @Autowired
    QOSRepository qosRepository;

    @Autowired
    RefQoSRepository refQoSRepository;

    @Autowired
    SelectionPageRepository selectionPageRepository;

    @Autowired
    ChoiceRepository choiceRepository;

    @Autowired
    RuleRepository ruleRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    ServiceManager serviceManager;

    /**
     * Create Or Update Service - Basic settings
     * @param existingService
     * @param newService
     */
    public void createOrUpdateService (Service existingService, Service newService) {

        if(existingService!=null && newService!=null) {

            //Clean up
            ruleRepository.delete(existingService.getRules());
            existingService.setRules(null);
            qosRepository.delete(existingService.getQos());
            existingService.setQos(null);
            selectionPageRepository.delete(existingService.getOptions()); // delete out any existing options.
            existingService.setOptions(null);
            serviceRepository.saveAndFlush(existingService);

            existingService.setAddOn(newService.isAddOn());
            existingService.setAllowance(new Long(newService.getAllowance()));
            existingService.setCharge(new Float(newService.getCharge()));
            existingService.setDataSelected(newService.isDataSelected());
            //existingService.setDateCreated(new Date());
            existingService.setLastUpdate(new Date());

            Set<Application> apps = new HashSet<Application>();
            for (Application app : newService.getAppsClassification()) {
                app = appRepository.findByName(app.getName());
                apps.add(app);
            }

            existingService.setAppsClassification(apps);
            existingService.setMaxNumberOfApps(newService.getMaxNumberOfApps());
            existingService.setName(newService.getName());
            existingService.setOverageCharge(newService.getOverageCharge());
            existingService.setPriority(existingService.getPriority());
            existingService.setRbmProductID(existingService.getRbmProductID());
            existingService.setSpoID(newService.getSpoID());
            existingService.setState(newService.getState());
            existingService.setTestSelected(newService.isTestSelected());
            existingService.setUserName(newService.getUserName());
            existingService.setVispServiceID(newService.getVispServiceID());
            existingService.setVoiceSelected(newService.isVoiceSelected());
            existingService.setOffer(existingService.getOffer());
            //existingService.setRatingGroup(newService.getRatingGroup());
            //qosRepository.delete(existingService.getQos());
            //existingService.setQos(null);
            //existingService.setQos(newService.getQos());

            serviceRepository.save(existingService);
        }

    }

    public static final Comparator<QoS> QOS_COMPARATOR = new Comparator<QoS>() {
        @Override
        public int compare(QoS o1, QoS o2) {
            if (o1.getChargePerDay()!=null && o2.getChargePerDay()!=null) {
                return o1.getChargePerDay().compareTo(o2.getChargePerDay());
            } else {
                return o1.getQosId().compareTo(o2.getQosId());
            }
        }
    };

    /**
     * Create or Updaate Service Questions for QOS
     * @param existingService
     * @param newService
     */
    public void createOrUpdatedServiceQuestionForQoS (Service existingService, Service newService) {

        if (newService!=null) {

            Set<QoS> qoss = newService.getQos(); // new service qos
            //Order elements as per Charges
            ServiceQuestion newQuestion = null;

            if (qoss!=null && !qoss.isEmpty()) {

                List<QoS> qoSList = new ArrayList<>(qoss);
                Collections.sort(qoSList, QOS_COMPARATOR);
                qoss = new LinkedHashSet<>(qoSList);

                //add new qos to existing service qos
                existingService.setQos(newService.getQos());

                //create a new service question
                newQuestion = new ServiceQuestion();
                newQuestion.setMaxSelectedOptions(1);
                newQuestion.setDateCreated(new Date());

                List<Choice> options = new ArrayList<Choice>();
                int defaultCount = 0;

                for (QoS qos : qoss) {

                    RefQoS qosRef = refQoSRepository.findByName(qos.getName());
                    Rule rule = null;

                    Choice choice = new Choice();
                    choice.setTitle(qos.getName());
                    choice.setColorCode(qosRef.getDefaultColorCodeHex());
                    choice.setServiceQuestion(newQuestion);
                    choice.setChargePerDay(qos.getChargePerDay());
                    choice.setDateCreated(new Date());

                    if (defaultCount == 0) {
                        choice.setSelected(true);
                        choice.setDefaultChoice(true);
                    } else {
                        choice.setSelected(false);
                    }

                    options.add(choice);

                    newQuestion.setChoice(options);
                    newQuestion.setService(existingService);

                    Set<ServiceQuestion> newServiceQuestionSet = new HashSet<ServiceQuestion>();

                    if (existingService.getOptions()==null) {
                        newServiceQuestionSet.add(newQuestion);
                        existingService.setOptions(newServiceQuestionSet);
                    } else {
                        existingService.getOptions().add(newQuestion);
                    }

                    if (defaultCount == 0) {
                        if (qos.getTimeFrame() == null || qos.getTimeFrame().getFrom() == null) {
                            rule = new Rule(qos.getName(), Rule.qosRuleType, Rule.byTimeExpressionType,
                                    "between 00:00:00 EST and 23:59:59 EST");
                        } else {
                            rule = new Rule(qos.getName(), Rule.qosRuleType, Rule.byTimeExpressionType,
                                    "between " + qos.getTimeFrame().getFrom() + " and " + qos.getTimeFrame().getTo());
                        }

                        //default attribute to be set. First value in the list.
                        if (qos.getSpeed() != null && !qos.getSpeed().trim().isEmpty()) {
                            rule.addAttribute("bandwidth", qos.getSpeed().toUpperCase());
                        }

                        rule.setDateCreated(new Date());
                        rule.setService(existingService);
                        existingService.addRule(rule);
                    }
                    defaultCount++;
                }

                /*for (Rule rule : existingService.getRules()) {
                    rule.setService(existingService);
                }*/

            }
        }
    }

    /**
     * Service Question update for Time frame
     * @param existingService
     * @param newService
     */
    public void createOrUpdatedServiceQuestionForTimeframe (Service existingService, Service newService) {
        //Time Frames
        Set<TimeFrame> timeFrames = newService.getTimeClassification();
        Set<TimeFrame> existingTimeFrames = existingService.getTimeClassification();

        if (timeFrames != null) {

            //Clean up
            if (existingTimeFrames!=null && !existingTimeFrames.isEmpty()) {

                timeFrameRepository.delete(existingTimeFrames);
                existingTimeFrames.clear();
                existingService.setTimeClassification(null);
                //note : don't delete the options as its just default not set through CPI.
            }

            //Reset
            for (TimeFrame t : timeFrames) {
                t.setDateCreated(new Date());
                timeFrameRepository.save(t);
            }

            existingService.setTimeClassification(timeFrames);

            for (TimeFrame timeFrame : timeFrames) {

                if (existingService.getRules()!=null) {
                    for (Rule rule : existingService.getRules()) {
                        if (rule.getRuleType().equalsIgnoreCase(Rule.classficationRuleType)
                                && Rule.byTimeExpressionType.equalsIgnoreCase(rule.getExpressionType())) {
                            rule.setExpression("between " + timeFrame.getFrom() + " and " + timeFrame.getTo());
                        }
                    }
                } else {
                    Rule rule = new Rule("classification", Rule.classficationRuleType, Rule.byTimeExpressionType,
                            "between " + timeFrame.getFrom() + " and " + timeFrame.getTo());
                    rule.setDateCreated(new Date());
                    rule.setService(existingService);
                    existingService.addRule(rule);
                }
            }
        }

    }

    /**
     * Service Question update for Time frame
     * @param existingService
     * @param newService
     */
    public void createOrUpdatedServiceQuestionForApps (Service existingService, Service newService) {

        if (existingService != null) {
            if (existingService.getAppsClassification() != null
                    && !existingService.getAppsClassification().isEmpty()) {

                appRepository.delete(existingService.getAppsClassification());
                existingService.setAppsClassification(null);
                if (existingService != null && existingService.getOptions() != null) {
                    for (ServiceQuestion question : existingService.getOptions()) {
                        choiceRepository.delete(question.getChoice());
                        question.setChoice(null);
                    }
                }
                /*if (existingService.getRules()!=null) {
                    ruleRepository.delete(existingService.getRules());
                }
                existingService.setRules(null);*/
                if (existingService != null && existingService.getOptions() != null) {
                    selectionPageRepository.delete(existingService.getOptions());
                }
                existingService.setOptions(null);


                Set<Application> oldApplications = newService.getAppsClassification();
                Set<Application> applications = oldApplications;
                if (oldApplications != null) {
                    applications = new HashSet<Application>();
                    for (Application app : oldApplications) {
                        Application newApp = appRepository.findByName(app.getName());

                        applications.add(newApp);
                    }
                    existingService.setAppsClassification(applications);
                }

                try {
                    if (applications != null) {
                        String s = "";
                        int i = 0;
                        for (Application app : applications)
                            if (i++ < newService.getMaxNumberOfApps())
                                s += app.getVispName() + " , ";

                        if (s != null && !s.equals("")) {
                            int y = s.lastIndexOf(",");
                            s = s.substring(0, y - 1);
                        } else {
                            s = "";
                        }

                        if (s != null && !s.trim().isEmpty()) {
                            Rule rule = new Rule("classification", Rule.classficationRuleType, Rule.byApplicationExpressionType, s);
                            rule.setDateCreated(new Date());
                            existingService.addRule(rule);
                        }

                        ServiceQuestion question = new ServiceQuestion();
                        question.setMaxSelectedOptions(newService.getMaxNumberOfApps());
                        question.setDateCreated(new Date());

                        List<Choice> options = new ArrayList<Choice>();

                        for (Application app : applications) {
                            Choice o = new Choice();
                            o.setTitle(app.getName());
                            o.setSelected(false);
                            o.setServiceQuestion(question);
                            options.add(o);//
                        }

                        if (options != null && !options.isEmpty()) {
                            question.setChoice(options);
                            question.setSelectionType("Dropdown");
                            question.setService(existingService);
                            Set<ServiceQuestion> uiSelections = newService.getOptions();
                            if (uiSelections == null)
                                uiSelections = new HashSet<ServiceQuestion>();
                            uiSelections.add(question);
                            existingService.setOptions(uiSelections);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
