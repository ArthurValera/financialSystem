package com.financial.system.financial.system.service.recurrence;

import com.financial.system.financial.system.model.RecurrenceType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.Map;

@Configuration
public class RecurrencePolicyConfig {

    @Bean
    public RecurrencePolicy weeklyRecurrencePolicy(){
        return new WeeklyRecurrencePolicy();
    }
    @Bean
    public RecurrencePolicy monthlyRecurrencePolicy(){
        return new MonthlyRecurrencePolicy();
    }
    @Bean
    public RecurrencePolicy yearlyRecurrencePolicy(){
        return new YearlyRecurrencePolicy();
    }

    @Bean
    public Map <RecurrenceType, RecurrencePolicy> recurrencePolicyMap(
        RecurrencePolicy weeklyRecurrencePolicy,
        RecurrencePolicy monthlyRecurrencePolicy,
        RecurrencePolicy yearlyRecurrencePolicy)
    {
        Map<RecurrenceType, RecurrencePolicy> map = new EnumMap<>(RecurrenceType.class);
        map.put(RecurrenceType.WEEKLY, weeklyRecurrencePolicy);
        map.put(RecurrenceType.MONTHLY, monthlyRecurrencePolicy);
        map.put(RecurrenceType.YEARLY, yearlyRecurrencePolicy);
        return map;
    }


}
