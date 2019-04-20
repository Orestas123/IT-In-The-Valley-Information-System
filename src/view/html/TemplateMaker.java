
package view.html;

import static j2html.TagCreator.attrs;
import static j2html.TagCreator.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import javafx.collections.ObservableList;
import model.Template;
import model.TemplateDrug;

/**
 */
public class TemplateMaker {
    
    

    public String makeTemplate(Template template, ObservableList<TemplateDrug> drugs) {
        
        String string = html(
                head(
                        title("Template")
                ),
                body(
                        div(
                                p(
                                        b(""),
                                        br(),
                                        span(template.getCc())
                                ),
                                p(
                                        b(""),
                                        br(),
                                        span(template.getOe())
                                ),
                                p(
                                        b(""),
                                        br(),
                                        span(template.getDd())
                                ),
                                 p(
                                        b(""),
                                        br(),
                                        span(template.getLab_workup())
                                ),
                                  p(
                                        b(""),
                                        br(),
                                        span(template.getAdvice())
                                )
                        ).withStyle("display:inline;float:left;width:40%;"),
                        div(
                                h1("Advertisement:"),
                                ol(
                                        each(drugs, drug -> tr(
                                        li(i(drug.getType() + " "), b(" " + drug.getDrug_name()), span(" " + drug.getStrength() + " "), ul(
                                                li(drug.getDose() + " " +drug.getDuration()).withStyle("list-style: none"),
                                                li(drug.getAdvice()).withStyle("list-style: none")
                                        ).withStyle("padding-left: 10px"))
                                ))
                                )
                        ).withStyle("display:inline;float:left;width:60%;")
                )
        ).render();

        return string;
    }

}
