function initTable() {
    layui.table.render({
        elem: '#table'
        , height: 700
        , url: '/sys/sys-menu/page/' //数据接口
        , page: true //开启分页
        , cols: [[ //表头
            {
                field: 'id', title: 'ID', width: 60, sort: true, fixed: 'left'
            },
            {
                field: 'name', title: '名称', width: 150,
            }, {
                field: 'url', title: 'url', width: 250
            }
            ,  {
                field: 'pid', title: '父ID', width: 80, templet: function (sysMenu) {
                    console.log(table);
                    return sysMenu.pid;
                }
            },  {fixed: 'right', width: 150, align: 'center', toolbar: '#barDemo'}
        ]], request: {
            pageName: 'current' //页码的参数名称，默认：page
            , limitName: 'size' //每页数据量的参数名，默认：limit
        },
        parseData: function (res) { //res 即为原始返回的数据
            console.log(res);
            return {
                "code": res.code, //解析接口状态
                "msg": res.msg, //解析提示文本
                "count": res.data.total, //解析数据长度
                "data": res.data.records //解析数据列表
            };
        }, response: {
            statusName: 'code' //规定数据状态的字段名称，默认：code
            , statusCode: 200 //规定成功的状态码，默认：0
            , msgName: 'msg' //规定状态信息的字段名称，默认：msg
            , countName: 'count' //规定数据总数的字段名称，默认：count
            , dataName: 'data' //规定数据列表的字段名称，默认：data
        }

    });
    layui.table.on('tool(table)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                menu.del(data.id, function (r) {
                    obj.del();
                    console.log(r);
                    layer.close(index);
                })
            });
        } else if (obj.event === 'edit') {
            console.log(data);
            vm.$data.sysMenu = data;
            vm.openForm("修改");
        }
    });
    $('#form').hide();

}

var menu = {

    urlHead: "/sys/sys-menu",

    save: function (data, call) {
        $.ajax({
            type: "POST",
            url: menu.urlHead + (data.id ? "/update" : "/add"),
            contentType: "application/json",
            data: JSON.stringify(data),
            success: call
        });
    },
    del: function (id, call) {
        $.get(this.urlHead + "/del", {"id": id}, call)
    },
    tree: function (id, call) {
        $.get(this.urlHead + "/tree?id="+id, call)
    }

}


var vm = new Vue({
    el: '#page',
    data: {
        formIndex: 0,
        sysMenu: {
            url: "",
            id: null,
            pid: null,
            name: ""
        },
        tree: []
    }, mounted: function () {
        initTable();
    },watch:{

    },
    updated: function(){
         if(vm.$data.tree)
            layui.form.render('select')


    },

    methods: {

        init: function () {

        },

        save: function () {
            menu.save(vm.$data.sysMenu, function (r) {
                if (r.code === 200) {
                    vm.close();
                    initTable();
                } else {
                    alert(r.msg);
                }
                $('#form').hide();
            })
        }, del: function (id, call) {
            menu.del(id, function (res) {

            })
        },
        close: function () {
            $('#form').hide();
            layer.close(this.formIndex)
        },
        openForm: function (title) {
            $('#form').show();
            this.initForm();
            this.$data.formIndex = layer.open({
                type: 1,
                title: vm.$data.sysMenu.id?"修改":"添加",
                area: ['600px', '500px'],
                content: $('#form') ,//这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                cancel: function(index, layero){
                    $('#form').hide();
                        layer.close(index)
                    return true;
                }

            });

        },
        initForm() {
            menu.tree(0,function (res) {
                vm.$data.tree = res;
                console.log(res);
            })


            layui.form.on("select(parentMenu)",function (data) {
                console.log(data)
                    vm.$data.sysMenu.pid=data.value;
            })
            layui.form.render();
        }
    }
})





