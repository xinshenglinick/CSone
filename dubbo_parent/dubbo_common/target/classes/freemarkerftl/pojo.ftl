package com.xsl.pojo;

import java.io.Serializable;

public class ${entityName} implements Serializable{
<#list fieldList as var>
     private ${var[1]} ${var[0]};//${var[2]}

</#list>

<#list fieldList as var>
    public void set${var[4]}(${var[1]} ${var[0]}){
        this.${var[0]}=${var[0]};
    }

    public ${var[1]} get${var[4]}(){
        return ${var[0]};
    }

</#list>
}