

const ap = new APlayer({
    container: document.getElementById('aplayer'),
    listFolded: false,//列表默认折叠
    listMaxHeight: 100,//列表最大高度
    lrcType: 3, //此为歌词格式，没有歌词可以直接删掉这一行
    autoplay: true,
    fixed: true,
    theme: '#b7daff',
    loop: 'all',
    mutex: true,
    "audio": [{"artist":"Sasha Sloan","cover":"https://p2.music.126.net/yKjpbkjawkKABkHy818gOQ==/109951164175679778.jpg?param=130y130","lrc":"/lrc/Dancing With Your Ghost.lrc","name":"Dancing With Your Ghost","url":"https://music.163.com/song/media/outer/url?id=1374329431"},{"artist":"王大毛","cover":"https://p1.music.126.net/5M7Qvv32HaFmMrLN8uehuA==/109951163293243877.jpg?param=130y130","lrc":"/lrc/去年夏天.lrc","name":"去年夏天","url":"https://music.163.com/song/media/outer/url?id=557581476"},{"artist":"中孝介","cover":"https://p1.music.126.net/25DGuAa24cQQthqeLOBiyg==/7989051488911119.jpg?param=130y130","lrc":"/lrc/花海.lrc","name":"花海","url":"https://music.163.com/song/media/outer/url?id=26111503"},{"artist":"TRA$H","cover":"https://p2.music.126.net/J1OXPgff80ZmF9uyapQzbA==/18522372883513330.jpg?param=130y130","lrc":"/lrc/ＡＬＯＮＥ.lrc","name":"ＡＬＯＮＥ","url":"https://music.163.com/song/media/outer/url?id=494645920"},{"artist":"米津玄師","cover":"https://p2.music.126.net/A-VUmkAaUVQ88iXBrsbQ1A==/109951163561900568.jpg?param=130y130","lrc":"/lrc/LOSER.lrc","name":"LOSER","url":"https://music.163.com/song/media/outer/url?id=512359195"},{"artist":"许嵩","cover":"https://p2.music.126.net/OjibHiyRong4S0RgBFp-Pw==/2301277836958388.jpg?param=130y130","lrc":"/lrc/你若成风.lrc","name":"你若成风","url":"https://music.163.com/song/media/outer/url?id=5255987"},{"artist":"Yuna","cover":"https://p2.music.126.net/-K5BGTrjHLiwhwd11eXixg==/109951163718521323.jpg?param=130y130","lrc":"/lrc/Better Now.lrc","name":"Better Now","url":"https://music.163.com/song/media/outer/url?id=1331821429"},{"artist":"Lenka","cover":"https://p1.music.126.net/RbiJjFAmdPhzaqpM6nAczw==/109951164841022611.jpg?param=130y130","lrc":"/lrc/The Show.lrc","name":"The Show","url":"https://music.163.com/song/media/outer/url?id=16607998"},{"artist":"Beth","cover":"https://p2.music.126.net/2-l6KNTqA62nLdGxSvkoZg==/109951163575046014.jpg?param=130y130","lrc":"/lrc/Let Me Down Slowly .lrc","name":"Let Me Down Slowly ","url":"https://music.163.com/song/media/outer/url?id=1313591404"},{"artist":"SLANDER / Dylan Matthew","cover":"https://p2.music.126.net/dTC6xKCa2MWLgl7ZSJm8HA==/109951164062390886.jpg?param=130y130","lrc":"/lrc/Love is Gone.lrc","name":"Love is Gone","url":"https://music.163.com/song/media/outer/url?id=1364247901"},{"artist":"dylanf","cover":"https://p1.music.126.net/-ShCoe12zt2C2mPQgaq0ZQ==/109951162915837220.jpg?param=130y130","lrc":"/lrc/卡农（经典钢琴版）.lrc","name":"卡农（经典钢琴版）","url":"https://music.163.com/song/media/outer/url?id=478507889"},{"artist":"Alexi Noa","cover":"https://p1.music.126.net/uKyamUfWL0qfFvJNrAVkCQ==/109951163676771046.jpg?param=130y130","lrc":"/lrc/Send It.lrc","name":"Send It","url":"https://music.163.com/song/media/outer/url?id=1326840518"},{"artist":"B.o.B / Bruno Mars","cover":"https://p2.music.126.net/LpNeJdD3VtiThH5uIi62Hg==/1698745464926789.jpg?param=130y130","lrc":"/lrc/Nothing On You.lrc","name":"Nothing On You","url":"https://music.163.com/song/media/outer/url?id=5100769"},{"artist":"Hawk Nelson","cover":"https://p1.music.126.net/UR8jAfqus_o1j_QkaYkZ_g==/109951163664241365.jpg?param=130y130","lrc":"/lrc/Sold Out.lrc","name":"Sold Out","url":"https://music.163.com/song/media/outer/url?id=31010566"},{"artist":"Matte / Ember Island","cover":"https://p2.music.126.net/1LrtvH8EpKb5iHKR9qEU0Q==/109951163063843501.jpg?param=130y130","lrc":"/lrc/Umbrella (Matte Remix).lrc","name":"Umbrella (Matte Remix)","url":"https://music.163.com/song/media/outer/url?id=518904426"},{"artist":"Fool's Garden","cover":"https://p1.music.126.net/r3i_ohvymLmdMpiF-dcN2A==/1353498813846118.jpg?param=130y130","lrc":"/lrc/Lemon Tree.lrc","name":"Lemon Tree","url":"https://music.163.com/song/media/outer/url?id=27180681"},{"artist":"Reynard Silva","cover":"https://p2.music.126.net/JyPsd_g00M-4mqXLLtHncw==/5984641790343690.jpg?param=130y130","lrc":"/lrc/The Way I Still Love You.lrc","name":"The Way I Still Love You","url":"https://music.163.com/song/media/outer/url?id=28718313"},{"artist":"Ava Max / Syn Cole","cover":"https://p2.music.126.net/dntPpej_HoH6CyAogRnbew==/109951164911660483.jpg?param=130y130","lrc":"/lrc/Salt (Syn Cole Remix).lrc","name":"Salt (Syn Cole Remix)","url":"https://music.163.com/song/media/outer/url?id=1441097233"},{"artist":"Davin大文 / 小郭雨儿","cover":"https://p1.music.126.net/sl9AJN53iMkn4v0WtitcLg==/109951164890315366.jpg?param=130y130","lrc":"/lrc/My beautiful.lrc","name":"My beautiful","url":"https://music.163.com/song/media/outer/url?id=1439374208"},{"artist":"Planet in Limbo","cover":"https://p2.music.126.net/wMjXwlpC_DFVJQ9MDmAy4Q==/109951164433255998.jpg?param=130y130","lrc":"/lrc/Natural.lrc","name":"Natural","url":"https://music.163.com/song/media/outer/url?id=1397573144"},{"artist":"ZAYN / Sia","cover":"https://p1.music.126.net/c3lWgFxA0nZQt1Pc5B1p7A==/109951163019943856.jpg?param=130y130","lrc":"/lrc/Dusk Till Dawn.lrc","name":"Dusk Till Dawn","url":"https://music.163.com/song/media/outer/url?id=504265014"},{"artist":"神山羊","cover":"https://p1.music.126.net/b8q7AOU3zQFPpayJ4k3J-Q==/109951163971418603.jpg?param=130y130","lrc":"/lrc/YELLOW.lrc","name":"YELLOW","url":"https://music.163.com/song/media/outer/url?id=1356248072"},{"artist":"Anna F","cover":"https://p2.music.126.net/l7vAbhmCkx4sBg6f9a6oPQ==/6069304185603873.jpg?param=130y130","lrc":"/lrc/Too Far.lrc","name":"Too Far","url":"https://music.163.com/song/media/outer/url?id=28442976"},{"artist":"SoMo","cover":"https://p2.music.126.net/-uGrdpcVwLIMEx6NXGaNrQ==/109951163168473869.jpg?param=130y130","lrc":"/lrc/50 Feet.lrc","name":"50 Feet","url":"https://music.163.com/song/media/outer/url?id=526116053"},{"artist":"MADILYN","cover":"https://p2.music.126.net/QaDXHrAwn1C6pp167uoMHw==/19085322835018013.jpg?param=130y130","lrc":"/lrc/Galway Girl.lrc","name":"Galway Girl","url":"https://music.163.com/song/media/outer/url?id=472361236"},{"artist":"Skylar Grey / Diddy-Dirty Money","cover":"https://p2.music.126.net/yEqg_-WA0Zr9R1k8oSo7vg==/827932255760295.jpg?param=130y130","lrc":"/lrc/Coming Home.lrc","name":"Coming Home","url":"https://music.163.com/song/media/outer/url?id=4038411"},{"artist":"Boyce Avenue / Bea Miller","cover":"https://p2.music.126.net/STR62HQZHcb76i3RrM9SCw==/109951164473866599.jpg?param=130y130","lrc":"/lrc/We Can't Stop.lrc","name":"We Can't Stop","url":"https://music.163.com/song/media/outer/url?id=29480187"},{"artist":"White Cherry","cover":"https://p2.music.126.net/-CFt0bZ07CkM8Chc6sM8Og==/109951164922344481.jpg?param=130y130","lrc":"/lrc/MELANCHOLY.lrc","name":"MELANCHOLY","url":"https://music.163.com/song/media/outer/url?id=1297742167"},{"artist":"Tamas Wells","cover":"https://p1.music.126.net/XbQhfWII58akaVQ6_xECEw==/6635552673830427.jpg?param=130y130","lrc":"/lrc/Valder Fields.lrc","name":"Valder Fields","url":"https://music.163.com/song/media/outer/url?id=2001320"},{"artist":"AGAM","cover":"https://p1.music.126.net/sAW4JrYhauNAotk6BIQiqg==/109951164684327278.jpg?param=130y130","lrc":"/lrc/Girls Like You（toby randall）.lrc","name":"Girls Like You（toby randall）","url":"https://music.163.com/song/media/outer/url?id=1421470173"},{"artist":"沈以诚","cover":"https://p2.music.126.net/CpwIPy2YUWC6ksF88eGNyw==/109951162931819035.jpg?param=130y130","lrc":"/lrc/椿.lrc","name":"椿","url":"https://music.163.com/song/media/outer/url?id=479598964"}]
});


