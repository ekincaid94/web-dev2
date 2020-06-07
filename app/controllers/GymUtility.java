
package controllers;

import models.Assessment;
import models.Member;

public class GymUtility {

    public static double calculatedBMI(Member member, Assessment assessment) {
        double bmiValue;

        if (member.assessments.size() != 0) {
            bmiValue = (100 * 100 * assessment.weight) / (member.height * member.height);
        } else {
            bmiValue = 0.0;
        }
        return bmiValue;
    }


    public static String BMIRange(double calculatedBMI) {
        String BMIRange = "";
        if (calculatedBMI < 16) {
            BMIRange = "SEVERELY UNDERWEIGHT";
        }
        if ((calculatedBMI >= 16) && (calculatedBMI < 18.5)) {
            BMIRange = "UNDERWEIGHT";
        }
        if ((calculatedBMI >= 18.5) && (calculatedBMI < 25)) {
            BMIRange = "NORMAL";
        }
        if ((calculatedBMI >= 25) && (calculatedBMI < 30)) {
            BMIRange = "OVERWEIGHT";
        }
        if ((calculatedBMI >= 30) && (calculatedBMI < 35)) {
            BMIRange = "MODERATELY OBESE";
        }
        if (calculatedBMI >= 35) {
            BMIRange = "SEVERELY OBESE";
        }
        return BMIRange;
    }

    public static boolean isIdealBodyWeight(Member member, Assessment assessment) {
        double height = member.height*100.0f;
        double weight = assessment.weight;
        double idealBodyWeight = 10;
        boolean isIdealBodyWeight = false;
        if (member.gender.equals("M"))
        {
            idealBodyWeight = 50.0f + 0.9f * (height - 152.0f);
        }
        if (member.gender.equals("F") || member.gender.equals("Unspecified"))
        {
            idealBodyWeight = 45.5f + 0.9f * (height - 152.0f);
        }
        if(idealBodyWeight == weight)
        {
            isIdealBodyWeight = true;
        }
        return (idealBodyWeight >= -0.2 && idealBodyWeight <= 0.2);
    }

}


