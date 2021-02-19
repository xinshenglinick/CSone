var app=angular.module('pyg', ['pagination']);//定义模块
app.controller('baseController' ,function($scope,$http){
//重新加载列表 数据
    $scope.reloadList=function(){
        //切换页码
        $scope.findPage( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    }
    //分页控件配置
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 3,
        itemsPerPage: 3,
        perPageOptions: [3, 5, 10],
        onChange: function(){
            $scope.reloadList();//重新加载
        }
    };
    //定义需要删除的集合
    $scope.selecteds=[];
    //复选框点击事件
    $scope.changeselected=function ($event,id) {
        if($event.target.checked){
            $scope.selecteds.push(id);
        } else{
            var index = $scope.selecteds.indexOf(id);
            $scope.selecteds.splice(index,1);
        }
    }
    //全选反选
    $scope.selectAll=function ($event) {
        if($event.target.checked){
            $scope.checkbox=true;
            //选中，集合要包含列表中的所有id
            angular.forEach($scope.brandlist,function (brand) {
                $scope.selecteds.push(brand.id);
            })
        }else{
            $scope.checkbox=false;
            $scope.selecteds=[];//没有选中，集合置空
        }
    }

})
