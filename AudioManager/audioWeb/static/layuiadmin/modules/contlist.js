/** layuiAdmin.std-v1.2.1 LPPL License By http://www.layui.com/admin/ */
;
layui.define(["table", "form"], function (t) {
    var e = layui.$,
        i = layui.table,
        n = layui.form;
    i.render({
        elem: "#LAY-app-content-list",
        url: layui.setter.serverUrl + "/audioList",
        cols: [
            [{
                type: "checkbox",
                fixed: "left"
            }, {
                field: "id",
                width: 100,
                title: "歌曲标识",
            }, {
                field: "song_id",
                title: "网易云歌曲id",
                minWidth: 100
            }, {
                field: "name",
                title: "歌曲名"
            }, {
                field: "artist",
                title: "歌手"
            }, {
                field: "url",
                title: "歌曲url",
            }
                , {
                field: "lrc",
                title: "歌词路径",
            }
                , {
                field: "cover",
                title: "歌曲封面地址",
            }
            , {
                field: "status",
                title: "歌曲状态",
                templet: "#buttonTpl",
                minWidth: 80,
                align: "center"
            }, {
                title: "操作",
                minWidth: 150,
                align: "center",
                fixed: "right",
                toolbar: "#table-content-list"
            }]
        ],
        page: true,
        limit: 5,
        limits: [3, 5, 7, 9],
        text: {none: '暂无歌曲数据'}
    }), i.on("tool(LAY-app-content-list)", function (t) {
        var e = t.data;
        "del" === t.event ? layer.confirm("确定删除此文章？", function (e) {
            t.del(), layer.close(e)
        }) : "edit" === t.event && layer.open({
            type: 2,
            title: "修改歌曲",
            content: "updAudio.html?id=" + e.id,
            maxmin: !0,
            area: ["550px", "550px"],
            btn: ["确定", "取消"],
            yes: function (e, i) {
                var l = window["layui-layer-iframe" + e],
                    a = i.find("iframe").contents().find("#layuiadmin-app-form-edit");
                l.layui.form.on("submit(layuiadmin-app-form-edit)", function (i) {
                    var l = i.field;
                    t.update({
                        label: l.label,
                        title: l.title,
                        author: l.author,
                        status: l.status
                    }), n.render(), layer.close(e)
                }), a.trigger("click")
            }
        })
    }), t("contlist", {})
});