package mentortools;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateIntervalValidator implements ConstraintValidator<DateIntervalValid, DateInterval> {
    @Override
    public boolean isValid(DateInterval dateInterval, ConstraintValidatorContext constraintValidatorContext) {
        //return dateInterval.getStartDate().isBefore(dateInterval.getEndDate());
        return false;
    }
}
