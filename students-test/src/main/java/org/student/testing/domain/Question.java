package org.student.testing.domain;

public class Question {


    String formulation;
    String A;
    String B;
    String C;
    String correctAnswer;

    public Question(String formulation, String a, String b, String c, String correctAnswer) {
        this.formulation = formulation;
        A = a;
        B = b;
        C = c;
        this.correctAnswer = correctAnswer;
    }


    public String getFormulation() {
        return formulation;
    }

    public String getA() {
        return A;
    }

    public String getB() {
        return B;
    }

    public String getC() {
        return C;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "formulation='" + formulation + '\'' +
                ", A='" + A + '\'' +
                ", B='" + B + '\'' +
                ", C='" + C + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}
