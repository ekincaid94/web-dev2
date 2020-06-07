package controllers;


        import java.util.List;

        import models.Member;
        import models.Assessment;
        import models.Trainer;
        import play.Logger;
        import play.mvc.Controller;

public class MemberCtrl extends Controller {
    public static void index(Long id) {
        Member member = Member.findById(id);
        List<Assessment> assessments = member.assessments;
        Logger.info("Member id = " + id);
        double BMI = 0;
        if (assessments.size() > 0) {
            BMI = Math.round(GymUtility.calculatedBMI(member, assessments.get(0)));
        }
        boolean isIdealBodyWeight = true;
        if (assessments.size() > 0) {
            isIdealBodyWeight = GymUtility.isIdealBodyWeight(member, assessments.get(0));
            String BMIRange = GymUtility.BMIRange(BMI);
            render("trainerview.html", member, assessments, (BMI), BMIRange, isIdealBodyWeight);
        }
    }


    public static void deleteMember(Long id)
    {
        Trainer trainer = Accounts.getLoggedInTrainer();
        Member member = Member.findById(id);
        Logger.info ("Removing" + member.firstname);
        trainer.members.remove(member);
        member.save();
        member.delete();
        redirect("/trainerdashboard");
    }

    public static void addComment(String comment, Long id)
    {
        Logger.info("Adding a Comment");
        Assessment assessment = Assessment.findById(id);
        assessment.save();
        Trainer trainer = Accounts.getLoggedInTrainer();
        Member member = Member.findById(id);
        List<Assessment> assessments = member.assessments;
        render("trainerviewdashboard.html", trainer, member, assessments, comment);
    }


}



