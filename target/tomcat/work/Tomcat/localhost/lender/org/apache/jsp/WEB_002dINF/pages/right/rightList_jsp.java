/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-01-10 09:23:18 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.pages.right;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class rightList_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/pub/js/zTree/css/zTreeStyle/demo.css\" type=\"text/css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/pub/js/zTree/css/zTreeStyle/zTreeStyle.css\" type=\"text/css\">\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/pub/js/zTree/js/jquery.ztree.core-3.5.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/pub/js/zTree/js/jquery.ztree.excheck-3.5.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/pub/js/zTree/js/jquery.ztree.exedit-3.5.js\"></script>\r\n");
      out.write("\t\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\tvar setting = {\r\n");
      out.write("\t\tasync : {\r\n");
      out.write("\t\t\tenable : true,//开启异步加载处理\r\n");
      out.write("\t\t\turl : encodeURI(encodeURI(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/right/list.html\")),\r\n");
      out.write("\t\t\tautoParam : [ \"id\" ],\r\n");
      out.write("\t\t\tdataFilter : filter,\r\n");
      out.write("\t\t\tcontentType : \"application/json\",\r\n");
      out.write("\t\t\ttype : \"get\"\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tview : {\r\n");
      out.write("\t\t\texpandSpeed : \"\",\r\n");
      out.write("\t\t\taddHoverDom : addHoverDom,\r\n");
      out.write("\t\t\tremoveHoverDom : removeHoverDom,\r\n");
      out.write("\t\t\tselectedMulti : false\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tedit : {\r\n");
      out.write("\t\t\tenable : true\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tdata : {\r\n");
      out.write("\t\t\tsimpleData : {\r\n");
      out.write("\t\t\t\tenable : true\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tcallback : {\r\n");
      out.write("\t\t\tbeforeRemove : beforeRemove,\r\n");
      out.write("\t\t\tbeforeRename : beforeRename,\r\n");
      out.write("\t\t}\r\n");
      out.write("\t};\r\n");
      out.write("\tfunction filter(treeId, parentNode, childNodes) {\r\n");
      out.write("\t\tvar nodes =  JSON.parse(childNodes.data);\r\n");
      out.write("\t\tif (!nodes)\r\n");
      out.write("\t\t\treturn null;\r\n");
      out.write("\t\tfor (var i = 0, l = nodes.length; i < l; i++) {\r\n");
      out.write("\t\t\tnodes[i].name = nodes[i].name.replace(/\\.n/g, '.');\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn nodes;\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction beforeRemove(treeId, treeNode) {\r\n");
      out.write("\t\tif (confirm(\"确认删除节点--\" + treeNode.name + \"--吗?\")) {\r\n");
      out.write("\t\t\tvar param = \"rightId=\" + treeNode.id;\r\n");
      out.write("\t\t\t$.post(encodeURI(encodeURI(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/right/deleteRight.html?\"\r\n");
      out.write("\t\t\t\t\t+ param)));\r\n");
      out.write("\t\t} else {\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction beforeRename(treeId, treeNode, newName) {\r\n");
      out.write("\t\tif (newName.length == 0) {\r\n");
      out.write("\t\t\talert(\"节点名称不能为空.\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tvar param = \"rightId=\" + treeNode.id + \"&name=\" + newName;\r\n");
      out.write("\t\talert(param);\r\n");
      out.write("\t\t$.post(encodeURI(encodeURI(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/right/editRight.html?\"\r\n");
      out.write("\t\t\t\t+ decodeURI(param))));\r\n");
      out.write("\t\treturn true;\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction addHoverDom(treeId, treeNode) {\r\n");
      out.write("\t\tvar sObj = $(\"#\" + treeNode.tId + \"_span\");\r\n");
      out.write("\t\tif (treeNode.editNameFlag || $(\"#addBtn_\" + treeNode.tId).length > 0)\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\tvar addStr = \"<span class='button add' id='addBtn_\" + treeNode.tId\r\n");
      out.write("\t\t\t\t+ \"' title='add node' onfocus='this.blur();'></span>\";\r\n");
      out.write("\t\tsObj.after(addStr);\r\n");
      out.write("\t\tvar btn = $(\"#addBtn_\" + treeNode.tId);\r\n");
      out.write("\t\tif (btn)\r\n");
      out.write("\t\t\tbtn.bind(\"click\", function() {\r\n");
      out.write("\t\t\t\tvar Ppname = prompt(\"请输入新节点名称\");\r\n");
      out.write("\t\t\t\tif (Ppname == null) {\r\n");
      out.write("\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t} else if (Ppname == \"\") {\r\n");
      out.write("\t\t\t\t\talert(\"节点名称不能为空\");\r\n");
      out.write("\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\tvar param =\"&pId=\"+ treeNode.id + \"&name=\" + Ppname;\r\n");
      out.write("\t\t\t\t\tvar zTree = $.fn.zTree.getZTreeObj(\"treeDemo\");\r\n");
      out.write("\t\t\t\t\t$.post(\r\n");
      out.write("\t\t\t\t\t\t\tencodeURI(encodeURI(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/right/save.html?\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t+ param)), function(data) {\r\n");
      out.write("\t\t\t\t\t\t\t\tif ($.trim(data) != null) {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tvar treenode = $.trim(data);\r\n");
      out.write("\t\t\t\t\t\t\t\t\tzTree.addNodes(treeNode, {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tpId : treeNode.id,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tname : Ppname\r\n");
      out.write("\t\t\t\t\t\t\t\t\t}, true);\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t})\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t};\r\n");
      out.write("\tfunction removeHoverDom(treeId, treeNode) {\r\n");
      out.write("\t\t$(\"#addBtn_\" + treeNode.tId).unbind().remove();\r\n");
      out.write("\t};\r\n");
      out.write("\t$(document).ready(function() {\r\n");
      out.write("\t\t$.fn.zTree.init($(\"#treeDemo\"), setting);\r\n");
      out.write("\r\n");
      out.write("\t});\r\n");
      out.write("</script>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write(".ztree li span.button.add {\r\n");
      out.write("\tmargin-left: 2px;\r\n");
      out.write("\tmargin-right: -1px;\r\n");
      out.write("\tbackground-position: -144px 0;\r\n");
      out.write("\tvertical-align: top;\r\n");
      out.write("\t*vertical-align: middle\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<div class=\"content_wrap\">\r\n");
      out.write("\t<div class=\"zTreeDemoBackground left\">\r\n");
      out.write("\t\t<ul id=\"treeDemo\" class=\"ztree\"></ul>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
