package org.example

import tornadofx.View
import tornadofx.borderpane
import tornadofx.center
import tornadofx.insets
import tornadofx.label

class HelloMessageView(
    private val name: String,
) : View("Hello World") {
    override val root =
        borderpane {
            center {
                label("Hello, $name") {
                    padding = insets(20)
                }
            }
        }
}
