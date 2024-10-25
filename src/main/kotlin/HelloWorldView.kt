package org.example

import javafx.beans.property.SimpleStringProperty
import tornadofx.View
import tornadofx.action
import tornadofx.borderpane
import tornadofx.button
import tornadofx.center
import tornadofx.field
import tornadofx.fieldset
import tornadofx.form
import tornadofx.textfield

class HelloWorldView : View("Hello World") {
    private val nameProperty = SimpleStringProperty()

    override val root =
        borderpane {
            center {
                form {
                    fieldset {
                        field("Name") {
                            textfield(nameProperty)
                        }
                        button("Confirm") {
                            action {
                                val name = nameProperty.value
                                if (name.isNotBlank()) {
                                    replaceWith(HelloMessageView(name))
                                }
                            }
                        }
                    }
                }
            }
        }
}
