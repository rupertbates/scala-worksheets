import com.github.jknack.handlebars.Handlebars

val handlebars = new Handlebars()

object Person {val name="rupert"}
val template = handlebars.compileInline("Hello {{this.name}}!")

System.out.println(template.apply(Person))