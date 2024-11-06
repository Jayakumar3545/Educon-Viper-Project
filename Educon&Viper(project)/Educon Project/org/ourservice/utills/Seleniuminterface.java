package org.ourservice.utills;

/**
 * Interface representing the functionalities related to filling out and submitting a form
 * in the Our Service application. This interface outlines the methods needed for collecting
 * user input and processing the form submission.
 */
public interface Seleniuminterface {

    /**@author Jayakumar
     * Fills out the form with the provided user details.
     *
     * @param name           The name of the applicant.
     * @param course         The course the applicant is applying for.
     * @param college        The college the applicant wishes to attend.
     * @param fatherName     The name of the applicant's father.
     * @param community      The community to which the applicant belongs.
     * @param phoneNumber    The applicant's phone number for contact purposes.
     * @param scholarshipAmount The amount of scholarship the applicant is applying for, if any.
     * @param email          The applicant's email address for correspondence.
     * @param message        Any additional message or comments from the applicant.
     */
    void fillOutForm(String name, String course, String college, String fatherName, 
                     String community, String phoneNumber, String scholarshipAmount, 
                     String email, String message);

    /**
     * Submits the filled-out form for processing.
     * This method triggers the form submission logic, which may include validation
     * of the form data and sending it to the backend or relevant service.
     */
    void submitForm();
}
