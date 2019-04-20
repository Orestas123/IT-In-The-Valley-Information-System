
package view.html;

import getway.AuthGetway;
import static j2html.TagCreator.b;
import static j2html.TagCreator.body;
import static j2html.TagCreator.br;
import static j2html.TagCreator.div;
import static j2html.TagCreator.each;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.h3;
import static j2html.TagCreator.head;
import static j2html.TagCreator.hr;
import static j2html.TagCreator.html;
import static j2html.TagCreator.i;
import static j2html.TagCreator.li;
import static j2html.TagCreator.ol;
import static j2html.TagCreator.p;
import static j2html.TagCreator.span;
import static j2html.TagCreator.table;
import static j2html.TagCreator.td;
import static j2html.TagCreator.title;
import static j2html.TagCreator.tr;
import static j2html.TagCreator.ul;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javafx.collections.ObservableList;
import model.Patient;
import model.Prescription;
import model.PrescriptionDrug;
import model.User;

/**
 */
public class PrescriptionMaker {
    
    User user = new User();
    
    AuthGetway auth = new AuthGetway();

    public String makePrescription(Prescription prescription, ObservableList<PrescriptionDrug> drugs, Patient patient) {
        
        user = auth.getUser();
        String phone = user.getShoePhoneInPrescription() == 1 ? "ID : "+ user.getPhoneNumber() : "";
        String address = user.getShowAddressInPrescription() == 1 ? user.getAddress() : "";
        String sex = patient.getSex() == 1 ? "Cyber-Security" : patient.getSex() == 2 ? "IT-Tech" : "Other";
        String string = html(
                head(
                        title("Magazine Issue")
                ),
                body(
                        h2(user.getFullName()),
                        p(
                                b(user.getInfo()),
                                br(),
                                span(phone),
                                br(),
                                span(address)
                        ),
                        hr(),
                        table().with(
                                tr().with(
                                        td().with(
                                                span("Magazine : " + patient.getName())
                                        ),
                                        td().with(
                                                span("Type : " + sex)
                                        ),
                                        td().with(
                                                span("Days : " + age(patient, prescription.getDate()) + " ")
                                        ),
                                        td().with(
                                                span("Date : " + prescription.getDate())
                                        )
                                )
                        ).withStyle("width:100%;"),
                        hr(),
                        div(
                                h1(
                                        h1(""),
                                        h1(),
                                        h1(prescription.getCc())
                                ),
                                p(
                                        b(""),
                                        br(),
                                        span(prescription.getOe())
                                ),
                                p(
                                        b(""),
                                        br(),
                                        span(prescription.getDd())
                                ),
                                p(
                                        b(""),
                                        br(),
                                        span(prescription.getDd())
                                ),
                                p(
                                        b(""),
                                        br(),
                                        span(prescription.getLabWorkUp())
                                ),
                                p(
                                        b(""),
                                        br(),
                                        span(prescription.getAdvice())
                                ),
                                p(
                                        b(""),
                                        br(),
                                        span(prescription.getNextVisit())
                                )
                        ).withStyle("display:inline;float:left;width:40%;"),
                        div(
                                h1("Advertisement:"),
                                ol(
                                        each(drugs, drug -> tr(
                                                li(i(drug.getDrugType() + " "),  ul(
                                                li(drug.getDrugDose()).withStyle("list-style: none"),
                                                li(drug.getDrugAdvice()).withStyle("list-style: none")
                                        ).withStyle("padding-left: 10px"))
                                ))
                                )
                        ).withStyle("display:inline;float:left;width:60%;"),
                        div(
                                p(
                                        p("This magazine page has been generated by Orestas Augustinaitis project, made for Applied Software Engineering Assesment. ").withStyle("font-size:9px;"),
                                        p("Please visit my university website: http://www.uwl.ac.uk").withStyle("font-size:9px;")
                                )
                        ).withStyle("position: absolute;bottom:0")
                )
        ).render();

        return string;
    }

    private String age(Patient patient, String date) {
        String age = "";
        LocalDate birthdate = patient.getDateOrBirth();
        LocalDate now = LocalDate.parse(date);
        age = String.valueOf(ChronoUnit.YEARS.between(birthdate, now));
        return age;
    }
}
