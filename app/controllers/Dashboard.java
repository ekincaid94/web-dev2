package controllers;

import models.Assessment;
import models.Trainer;
import models.Member;
import play.Logger;
import play.mvc.Controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Dashboard extends Controller{

          public static void indexMember () {
            Logger.info("Rendering Dashboard");
            Member member = Accounts.getLoggedInMember();
            List<Assessment> assessments = member.assessments;
            double BMI = 0;
            if (assessments.size() > 0) {
              BMI = Math.round(GymUtility.calculatedBMI(member, assessments.get(0)));
            }
            boolean isIdealBodyWeight = true;
            if (assessments.size() > 0) {
              isIdealBodyWeight = GymUtility.isIdealBodyWeight(member, assessments.get(0));
              String BMIRange = GymUtility.BMIRange(BMI);
              render("dashboard.html", member, assessments, (BMI), BMIRange, isIdealBodyWeight);
            }
          }

        public static void indexTrainer() {
          Logger.info("Rendering Trainer Dashboard");
          Trainer trainer = Accounts.getLoggedInTrainer();
          List<Member> members = Member.findAll();
          for (Member member : members) {
            render("trainerdashboard.html", trainer, members);
          }
        }


  public static void addAssessment(double weight,double chest,double thigh, double upperArm, double waist, double hips)
  {
    Member member = Accounts.getLoggedInMember();
    Assessment assessment = new Assessment(weight,chest,thigh,upperArm,waist,hips);
    member.assessments.add(0, assessment); //0 to add most recent to the top
    member.save();
    Logger.info("Adding Assessment");
    redirect("/dashboard" );
  }



  public static void deleteAssessment(Long id, Long assessmentid)
  {
    Member member = Member.findById(id);
    Assessment assessment = Assessment.findById(id);
    member.assessments.remove(assessment);
    member.save();
    assessment.delete();
    Logger.info("Deleting " + assessment.id);
    redirect("/dashboard");
  }



}

