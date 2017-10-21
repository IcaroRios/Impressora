<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>DASHBOARD - Sistema de Advogados</title>

</head>


<body>
	<form class="row form-box" method="post" action="/print" enctype="multipart/form-data">
		<input type="hidden" name="_token" value="{{{ csrf_token() }}}" />
		<h2>imprimir </h2>
		<div class="col-xs-10 col-xs-offset-1">
			<input required type="file"	data-role="magic-overlay"
		    data-edit="insertImage" id="arquivo" name= "arquivo"
		    accept=".pdf"/>
		</div>
		<div class="col-xs-10 col-xs-offset-1">
	    	<button class="btn filtrar">Imprimir</button>
		</div>
	</form>

</body>

</html>