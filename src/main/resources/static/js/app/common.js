const borderDangerClassName = 'border-danger';

const borderDanger = (elementId) => {
	var element = document.getElementById(elementId);
	element.className += ' ' + borderDangerClassName;
}

const removeBorderDanger = (...elementIds) => {
	
	for(let elementId of elementIds){
		document.getElementById(elementId).classList.remove(borderDangerClassName);
	}
}

const alertDiv = (message, type, alertPlaceholder) => {
  const wrapper = document.createElement('div')
  wrapper.innerHTML = [
    `<div class="alert alert-${type} alert-dismissible" role="alert">`,
    `   <div>${message}</div>`,
    '   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>',
    '</div>'
  ].join('');
  alertPlaceholder.append(wrapper);
};