function togglePanel(toggleBtn) {
	var buttonText = $(toggleBtn).text();
	var buttonId = $(toggleBtn).attr("id");
	var panelToShowId = buttonId.replace("toggleBtn", "").concat(
			"secondPartPanel");
	if (buttonText.indexOf("More") >= 0) {
		$(toggleBtn).text('Less');
		$(PrimeFaces.escapeClientId(panelToShowId)).css("display", "block");
	} else {
		$(toggleBtn).text('More');
		$(PrimeFaces.escapeClientId(panelToShowId)).css("display", "none");
	}
}