package com.intelligrape

class TinyMceTagLib {
    static namespace = "tinyMce"

    def importJs = { attrs, body ->
        String mode = 'textareas'
        String theme = 'simple'
        String editor_selector = 'mcesimple'
        out << """<script type="text/javascript" src="${g.resource(dir: pluginContextPath + '/js/tiny_mce', file: 'tiny_mce.js' )}"></script> """
        out << """<script type="text/javascript">"""
        out << initTinyMce(theme, mode, editor_selector)
        String toolbarPos = attrs.toolbarPosition?:'top'
        theme = "advanced"
        editor_selector = 'mceadvanced'
        out << initTinyMce(theme, mode, editor_selector, toolbarPos)
        out << """</script>"""


    }

    def renderEditor = { attrs, body ->
        String type = attrs['type']?"mce" + attrs['type']: "mce" + 'simple'
        attrs.remove('type')
        String cssClass = attrs.remove('class')
        String classToApply = cssClass + " " + type
        out << """<textarea """
        attrs.each{key, value->

          out << key + "=" + "'${value}'"
        }
        out << "class" + "=" + "'${classToApply}'"
        out << """>"""
        out << body()
        out << """</textarea>"""
    }

    def initTinyMce(String theme, String mode, String editor_selector, String toolbarPos = null){
        String initString = """tinyMCE.init({
                                mode : "${mode}",
                                theme : "${theme}",
                                editor_selector : "${editor_selector}",
                                theme_advanced_toolbar_location : "${toolbarPos}"
                            });"""

        return initString
    }
}
