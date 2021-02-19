app.controller('${entityNameLower}Controller' ,function($scope,$http,$controller,${entityNameLower}Service){
    $controller('baseController',{$scope:$scope});//继承
    //查询所有
    $scope.findAll=function () {
        ${entityNameLower}Service.findAll().success(function (data) {
            $scope.${entityNameLower}list=data;
            console.log($scope.${entityNameLower}list);
        })
    }
    $scope.searchEntity={};
    //查询分页列表
    $scope.findPage=function (pageNum,pageSize) {
        ${entityNameLower}Service.findPage(pageNum,pageSize,$scope.searchEntity).success(function (data) {
            $scope.${entityNameLower}list=data.recourds;
            $scope.paginationConf.totalItems=data.total;
        })
    }
    //回显
    $scope.getById=function (id) {
        ${entityNameLower}Service.getById(id).success(function (data) {
            $scope.entity=data;
        })
    }
    $scope.entity={};
    //保存
    $scope.save=function () {
        ${entityNameLower}Service.save($scope.entity).success(function (data) {
            if(data.success){
                $scope.entity={};
                $scope.reloadList();
            } else{
                alert(data.errmsg);
            }

        })
    }
    //批量删除
    $scope.deletebth=function () {
        ${entityNameLower}Service.deletbth($scope.selecteds).success(function (data) {
            if(data.success){
                $scope.selecteds=[];
                $scope.reloadList();
            } else{
                alert(data.errmsg);
            }
        })
    }
});