<!DOCTYPE html>
<html>
	<head lang="en">
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'experimentalPlateSet.label', default: 'ExperimentalPlateSet')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
		
		<g:javascript>
			window.expId = ${expId};
			window.templateId = ${templateId};
		</g:javascript>
		
		<asset:stylesheet href="jquery-ui.css"/>
	    <asset:stylesheet href="grid/style.css"/>
	    <asset:stylesheet href="grid/slick.grid.css"/>
	    <asset:stylesheet href="grid/slick-default-theme.css"/>
	    <asset:stylesheet href="grid/Grid.css"/>
	</head>
	<body>
		<div class="content-fluid ">
			<div class="row">
				<div class="col-xs-12 content-body">
					<h2 style="margin-left:15px">Create Plate
					    <small>
					        <span class="btn-group pull-right">
								<button id="copyPlate" class="btn btn-info btn-sm ui-state-disabled">Copy Plate</button>
								<button id="savePlate" type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#savePlateModal" style="margin-right:15px">Save Plate</button>
					        </span>
					    </small>
					</h2>
					<ol class="breadcrumb">
						<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
						<li><g:link controller="experimentalPlateSet" action="index">Experiments</g:link></li>
						<li><g:link controller="experimentalPlateSet" action="showactions" id="${expId}">Show Experiment</g:link></li>
						<li><g:link controller="experimentalPlateSet" action="selectTemplate" id="${expId}">Select Template</g:link></li> <!-- NEED TO SUPPLY EXP ID & Width/Height HERE !!! -->
						<li><g:link controller="experimentalPlateSet" action="createPlate">Assign Labels</g:link></li>		<!-- NEED TO SUPPLY EXP ID & TMP ID HERE !!! -->
					</ol>
					<g:render template="assignLabels"/>					
				</div>
			</div>
		</div>
	</body>
</html>
