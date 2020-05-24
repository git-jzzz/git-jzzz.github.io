

const ap = new APlayer({
    container: document.getElementById('aplayer'),
    listFolded: false,//列表默认折叠
    listMaxHeight: 100,//列表最大高度
    lrcType: 3, //此为歌词格式，没有歌词可以直接删掉这一行
	autoplay: true,
	fixed: true,
	loop: 'all',
	mutex: true,
    audio: [
        {
            name: 'Dancing With Your Ghost',
            artist: 'Sasha Sloan',
            url: '/mp3/Dancing With Your Ghost.mp3',
            lrc: '/mp3/Dancing With Your Ghost.lrc',
            theme: '#46718b'
        }
    ]
});