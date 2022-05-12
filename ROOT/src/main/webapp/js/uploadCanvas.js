uploadCanvasToServer = function() {
  const canvas = document.getElementById('canvas_crop');
  const imgBase64 = canvas.toDataURL('image/jpeg', 'image/octet-stream');
  const decodImg = atob(imgBase64.split(',')[1]);

  let array = [];
  for (let i = 0; i < decodImg .length; i++) {
    array.push(decodImg .charCodeAt(i));
  }

  const file = new Blob([new Uint8Array(array)], {type: 'image/jpeg'});
  const fileName = 'canvas_img_' + new Date().getMilliseconds() + '.jpg';
  let formData = new FormData();
  formData.append('file', file, fileName);

  $.ajax({
    type: 'post',
    url: '/upload/',
    cache: false,
    data: formData,
    processData: false,
    contentType: false,
    success: function (data) {
      alert('업로드를 완료했습니다')
    }
  })
};