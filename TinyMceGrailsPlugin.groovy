class TinyMceGrailsPlugin {
    // the plugin version
    def version = "0.1.2"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.1 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp", "grails-app/views/index.gsp"
    ]

    def author = "S Vivek Krishna"
    def authorEmail = "vivek[at]intelligrape.com"
    def title = "TinyMce"
    def description = '''\\ A plugin with the tinyMce editor javascript and tags to embed the editor in your GSP pages

                      '''

    def documentation = "http://grails.org/plugin/tiny-mce"

    def doWithWebDescriptor = { xml ->
    }

    def doWithSpring = {
    }

    def doWithDynamicMethods = { ctx ->
    }

    def doWithApplicationContext = { applicationContext ->
    }

    def onChange = { event ->
    }

    def onConfigChange = { event ->
    }
}
