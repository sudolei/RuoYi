//鏈浘閬楀繕鐨勯潚鏄�
//github  https://github.com/IFmiss/loading
//鏄剧ずLoading
(function($){
	$.fn.loading = function(options){
		var $this = $(this);
		var _this = this;
		return this.each(function(){
		    var loadingPosition ='';
		    var defaultProp = {
		    	direction: 				'column',												//鏂瑰悜锛宑olumn绾靛悜   row 妯悜
				animateIn: 	 			'fadeInNoTransform',    								//杩涘叆绫诲瀷
				title:                  '璇风◢绛�...',      										//鏄剧ず浠€涔堝唴瀹�
				name: 					'loadingName', 											//loading鐨刣ata-name鐨勫睘鎬у€�  鐢ㄤ簬鍒犻櫎loading闇€瑕佺殑鍙傛暟
				type: 			        'origin', 			  									//pic   origin  
				discription: 			'杩欐槸涓€涓弿杩�', 										//loading鐨勬弿杩�
				titleColor: 			'rgba(255,255,255,0.7)',								//title鏂囨湰棰滆壊
				discColor: 				'rgba(255,255,255,0.7)',								//disc鏂囨湰棰滆壊
				loadingWidth:           260,                									//涓棿鐨勮儗鏅搴idth
				loadingBg:        		'rgba(0, 0, 0, 0.6)',  									//涓棿鐨勮儗鏅壊
				borderRadius:     		12,                 									//涓棿鐨勮儗鏅壊鐨刡orderRadius
				loadingMaskBg:    		'transparent',          								//鑳屾櫙閬僵灞傞鑹�
				zIndex:           		1000001,              									//灞傜骇

				// 杩欐槸鍦嗗舰鏃嬭浆鐨刲oading鏍峰紡  
				originDivWidth:        	60,           											//loadingDiv鐨剋idth
				originDivHeight:       	60,           											//loadingDiv鐨凥eight

				originWidth:      		8,                  									//灏忓渾鐐箇idth
				originHeight:     		8,                  									//灏忓渾鐐笻eight
				originBg:         		'#fefefe',              								//灏忓渾鐐硅儗鏅壊
				smallLoading:     		false,                  								//鏄剧ず灏忕殑loading

				// 杩欐槸鍥剧墖鐨勬牱寮�   (pic)
				imgSrc: 				'http://www.daiwei.org/index/images/logo/dw.png',    	//榛樿鐨勫浘鐗囧湴鍧€
				imgDivWidth: 			80,           											//imgDiv鐨剋idth
				imgDivHeight: 			80,           											//imgDiv鐨凥eight

				flexCenter: 	 		false, 													//鏄惁鐢╢lex甯冨眬璁﹍oading-div鍨傜洿姘村钩灞呬腑
				flexDirection: 			'row',													//row column  flex鐨勬柟鍚�   妯悜 鍜� 绾靛悜				
				mustRelative: 			false, 													//$this鏄惁瑙勫畾relative
		    };


		    var opt = $.extend(defaultProp,options || {});

		    //鏍规嵁鐢ㄦ埛鏄拡瀵筨ody杩樻槸鍏冪礌  璁剧疆瀵瑰簲鐨勫畾浣嶆柟寮�
		    if($this.selector == 'body'){
		    	$('body,html').css({
		    		overflow:'hidden',
		    	});
		    	loadingPosition = 'fixed';
		    }else if(opt.mustRelative){
		    	$this.css({
			    	position:'relative',
			    });
			    loadingPosition = 'absolute';
		    }else{
		    	loadingPosition = 'absolute';
		    }

		    defaultProp._showOriginLoading = function(){
		    	var smallLoadingMargin = opt.smallLoading ? 0 : '-10px';
		    	if(opt.direction == 'row'){smallLoadingMargin='-6px'}

		    	//鎮诞灞�
		      	_this.cpt_loading_mask = $('<div class="cpt-loading-mask animated '+opt.animateIn+' '+opt.direction+'" data-name="'+opt.name+'"></div>').css({
			        'background':opt.loadingMaskBg,
			        'z-index':opt.zIndex,
			        'position':loadingPosition,
				}).appendTo($this);

		      	//涓棿鐨勬樉绀哄眰
				_this.div_loading = $('<div class="div-loading"></div>').css({
			        'background':opt.loadingBg,
			        'width':opt.loadingWidth,
			        'height':opt.loadingHeight,
			        '-webkit-border-radius':opt.borderRadius,
			        '-moz-border-radius':opt.borderRadius,
			        'border-radius':opt.borderRadius,
		      	}).appendTo(_this.cpt_loading_mask);

				if(opt.flexCenter){
					_this.div_loading.css({
						"display": "-webkit-flex",
						"display": "flex",
						"-webkit-flex-direction":opt.flexDirection,
						"flex-direction":opt.flexDirection,
						"-webkit-align-items": "center",
						"align-items": "center",
						"-webkit-justify-content": "center",
						"justify-content":"center",
					});
				}

				//loading鏍囬
	        	_this.loading_title = $('<p class="loading-title txt-textOneRow"></p>').css({
	        		color:opt.titleColor,
	        	}).html(opt.title).appendTo(_this.div_loading);

	        	//loading涓棿鐨勫唴瀹�  鍙互鏄浘鐗囨垨鑰呰浆鍔ㄧ殑灏忓渾鐞�
		     	_this.loading = $('<div class="loading '+opt.type+'"></div>').css({
			        'width':opt.originDivWidth,
			        'height':opt.originDivHeight,
		      	}).appendTo(_this.div_loading);

		     	//鎻忚堪
	        	_this.loading_discription = $('<p class="loading-discription txt-textOneRow"></p>').css({
	        		color:opt.discColor,
	        	}).html(opt.discription).appendTo(_this.div_loading);

	        	if(opt.type == 'origin'){
	        		_this.loadingOrigin = $('<div class="div-loadingOrigin"><span></span></div><div class="div-loadingOrigin"><span></span></div><div class="div_loadingOrigin"><span></span></div><div class="div_loadingOrigin"><span></span></div><div class="div_loadingOrigin"><span></span></div>').appendTo(_this.loading);
			      	_this.loadingOrigin.children().css({
				        "margin-top":smallLoadingMargin,
				        "margin-left":smallLoadingMargin,
				        "width":opt.originWidth,
				        "height":opt.originHeight,
				        "background":opt.originBg,
			      	});
	        	}	

	        	if(opt.type == 'pic'){
	        		_this.loadingPic = $('<img src="'+opt.imgSrc+'" alt="loading" />').appendTo(_this.loading);
	        	}	      


		      	//鍏抽棴浜嬩欢鍐掓场  鍜岄粯璁ょ殑浜嬩欢
			    _this.cpt_loading_mask.on('touchstart touchend touchmove click',function(e){
					e.stopPropagation();
					e.preventDefault();
			    });
		    };
		    defaultProp._createLoading = function(){
		    	//涓嶈兘鐢熸垚涓や釜loading data-name 涓€鏍风殑loading
		    	if($(".cpt-loading-mask[data-name="+opt.name+"]").length > 0){
		    		// console.error('loading mask cant has same date-name('+opt.name+'), you cant set "date-name" prop when you create it');
		    		return
		    	}
				
				defaultProp._showOriginLoading();
		    };
		    defaultProp._createLoading();
		});
	}

})(jQuery)

//鍏抽棴Loading
function removeLoading(loadingName){
	var loadingName = loadingName || '';
	$('body,html').css({
		overflow:'auto',
	});

	if(loadingName == ''){
		$(".cpt-loading-mask").remove();
	}else{
		var name = loadingName || 'loadingName';
		$(".cpt-loading-mask[data-name="+name+"]").remove();		
	}
}