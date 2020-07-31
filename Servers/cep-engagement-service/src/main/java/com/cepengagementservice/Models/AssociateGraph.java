package com.cepengagementservice.Models;

/**
 * This model is used to setup the skills graph for the associates
 * @author Unknown
 *
 */
public class AssociateGraph {
	

		@Override
		public String toString() {
			return "AssociateGraph [spiderTraineeGrades=" + spiderTraineeGrades + "]";
		}

		public GradeCategories getSpiderTraineeGrades() {
			return spiderTraineeGrades;
		}

		public void setSpiderTraineeGrades(GradeCategories spiderTraineeGrades) {
			this.spiderTraineeGrades = spiderTraineeGrades;
		}
		
		/**
		 * This class represents the different skill categories
		 * @author Unknown
		 *
		 */
		public class GradeCategories{
				@Override
			public String toString() {
				return "GradeCategories [traineeId=" + traineeId + ", assessmentType=" + assessmentType + ", score="
						+ score + ", week=" + week + ", weight=" + weight + "]";
			}

				public String getTraineeId() {
				return traineeId;
			}

			public void setTraineeId(String traineeId) {
				this.traineeId = traineeId;
			}

			public String getAssessmentType() {
				return assessmentType;
			}

			public void setAssessmentType(String assessmentType) {
				this.assessmentType = assessmentType;
			}

			public double getScore() {
				return score;
			}

			public void setScore(double score) {
				this.score = score;
			}

			public int getWeek() {
				return week;
			}

			public void setWeek(int week) {
				this.week = week;
			}

			public int getWeight() {
				return weight;
			}

			public void setWeight(int weight) {
				this.weight = weight;
			}

				public String traineeId;
				public String assessmentType;
			    public double score;
			    public int week;
			    public int weight;
			    
			    public GradeCategories() {}
			    
			    public GradeCategories(String traineeId, String assessmentType, double score, int week, int weight) {
			    	this.traineeId= traineeId;
			    	this.assessmentType= assessmentType;
			    	this.score= score;
			    	this.week= week;
			    	this.weight= weight;
			    }
		}
	    public AssociateGraph(){};
	    
	    public AssociateGraph(GradeCategories spiderTraineeGrades){
	    	this.spiderTraineeGrades = spiderTraineeGrades;
	      
	    };
	    public GradeCategories spiderTraineeGrades;

	}
