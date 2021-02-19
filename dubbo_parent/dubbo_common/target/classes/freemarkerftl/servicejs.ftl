app.service('${entityNameLower}Service' ,function($http){

    this.findAll=function () {
        return $http.get("../${entityNameLower}/findAll.do");
    }

    this.findPage=function (pageNum,pageSize,searchEntity) {
        return $http.post("../${entityNameLower}/pageList.do?pageSize="+pageSize+"&pageNum="+pageNum,searchEntity);
    }

    this.getById=function (id){
        return $http.get("../${entityNameLower}/getById.do?id="+id);
    }
    
    this.save =function (entity) {
        return $http.post("../${entityNameLower}/save.do",entity);
    }
    this.deletbth = function (selecteds) {
        return $http.get("../${entityNameLower}/deletebth.do?ids="+selecteds);
    }
})