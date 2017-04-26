
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object form_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class form extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String, style: String = "form"):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.43*/("""

"""),_display_(/*3.2*/defining(play.core.PlayVersion.current)/*3.41*/ { version =>_display_(Seq[Any](format.raw/*3.54*/("""

"""),format.raw/*5.1*/("""<link rel="stylesheet" media="screen" href="/@documentation/resources/style/main.css">

<section id="top">
    <div class="wrapper">
        <h1><a href="http://localhost:9000">"""),_display_(/*9.46*/message),format.raw/*9.53*/("""</a></h1>
    </div>
</section>

<div id="content" class="wrapper doc">
    <article>

        <h1>Welcome to Play</h1>

     <form method="POST" action="/addOne">
         <div>
             <h3>Name: </h3>
             <input type="text" id="nameInput"/>
         </div>
         <div>
             <h3>Surname:</h3>
         <input type="text" id="surnameInput"/>
         </div>
         <div>
             <h3>Biggest Hit:</h3>
            <input type="text" id="hitInput"/>
         </div>
         <br>
         <input type="submit" id="accept" value="Submit" onclick="return putIn()"/>
     </form>
    </article>

    <aside>
        <h3>Browse</h3>
       <table border="1" >
           <tr>
               <td>Name<td/>
           </tr>
           <tr>
               <td>Surname<td/>
           </tr>
           <tr>
               <td>Hit<td/>
           </tr>
       </table>

    </aside>

</div>
""")))}))
      }
    }
  }

  def render(message:String,style:String): play.twirl.api.HtmlFormat.Appendable = apply(message,style)

  def f:((String,String) => play.twirl.api.HtmlFormat.Appendable) = (message,style) => apply(message,style)

  def ref: this.type = this

}


}

/**/
object form extends form_Scope0.form
              /*
                  -- GENERATED --
                  DATE: Wed Apr 26 14:48:36 CEST 2017
                  SOURCE: /home/zlatan/Downloads/play-scala-starter-example/app/views/form.scala.html
                  HASH: 674e6c3efe7462258ab06901dc271bbd87be228a
                  MATRIX: 532->1|668->42|696->45|743->84|793->97|821->99|1025->278|1052->285
                  LINES: 20->1|25->1|27->3|27->3|27->3|29->5|33->9|33->9
                  -- GENERATED --
              */
          