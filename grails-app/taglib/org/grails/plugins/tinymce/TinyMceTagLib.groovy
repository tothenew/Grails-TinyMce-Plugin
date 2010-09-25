package org.grails.plugins.tinymce
/**
 * @author S Vivek Krishna
 *
 * The Tag library which provides the functionality to include TinyMce editor in the application
 * There are two tags, importJs and renderEditor to include the JavaScript files and render the editor
 * respectively.
 */
class TinyMceTagLib {
    static namespace = "tinyMce"
    /**
     * This tag has to be included in the <head> tag of the GSP to include the JS files which are required
     * to render the TinyMce editor, in the body, where specified.
     *
     * The position of the toolbar(for advanced editor type) can be specified here.
     * By default, the toolbar position is assumed to be at the top.
     * Valid values are "top" or "bottom"
     *
     * @param toolbarPosition
     */
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



    /**
     * This tag renders the tinyMce editor.
     * The main attribute that should be specified is "type" on the basis of which the simple or
     * advanced editor is rendered.
     *
     * @param type
     * @param all valid textarea attributes
     */
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

    /**
     * Function to construct the String which will initialize the TinyMce editor on the GSP. This is called
     * twice from the importJs tag, one for the simple and one for advanced editors.
     * @param theme
     * @param mode
     * @param editor_selector
     * @param toolbarPos
     * @return
     */
    String initTinyMce(String theme, String mode, String editor_selector, String toolbarPos = null){
        String initString = """tinyMCE.init({
                                mode : "${mode}",
                                theme : "${theme}",
                                editor_selector : "${editor_selector}",
                                theme_advanced_toolbar_location : "${toolbarPos}"
                            });"""

        return initString
    }
}
