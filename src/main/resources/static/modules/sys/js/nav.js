$(document).ready(function () {
    $.get("/sys/sys-menu/tree?id=0", function (res) {
        var html = "<ul class='layui-nav layui-nav-tree'>";
        var menu = res;
        console.log(menu)
        for (var i = 0; i < menu.length; i++) {
            html += "<li class='layui-nav-item'><a href='#'>" + menu[i].name + "</a>";
            if (menu[i].children) {
                html += "<dl class='layui-nav-child '>";
                var ch = menu[i].children;
                for (var j = 0; j < ch.length; j++) {
                    var menuDa = ch[j];
                    console.log(menuDa);
                    console.log(j)
                    html += "<dd><a href='javascript:;' data-options=" + `{url:"${menuDa.url}",id:"${menuDa.id}",title:"${menuDa.name}"}` + `>${menuDa.name}` + "</a></dd>"
                }
                html += "</dl>";
            }
            html += "</li>";
        }
        html += ("</ul>");
        $(html).appendTo($("#left-nav"));
        layui.use('element', function () {
            var element = layui.element;
            //获取hash来切换选项卡，假设当前地址的hash为lay-id对应的值
            // var layid = location.hash.replace(/^#test1=/, '');
            // element.tabChange('test1', layid); //假设当前地址为：http://a.com#test1=222，那么选项卡会自动切换到“发送消息”这一项

            //监听Tab切换，以改变地址hash值
            element.on('tab(tabs)', function (e) {
                element.on('tab(tabs)', function (data) {
                    var name = $(this).text().toString();
                    location.hash = 'menu=' + name.substring(0, name.length - 1);
                });
            });
        });
        $('.layui-nav-child a').click(function () {
            var options = eval('(' + $(this).data('options') + ')');
            var url = options.url;
            var title = options.title;
            var id = options.id;
            var exist = $("li[lay-id='" + id + "']").length; //判断是否存在tab
            if (exist == 0) {
                layui.element.tabAdd('tabs', {
                    title: title,
                    id: id,
                    content: '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>'
                });
            }
            layui.element.tabChange('tabs', id);
        });


    });
});
