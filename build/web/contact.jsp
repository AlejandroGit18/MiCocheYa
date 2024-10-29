<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    String title = "Contacto"; 
%>
<jsp:include page="master.jsp" />

<!-- Sección de Contacto estilizada con Bootstrap -->
<div class="container mt-5">
    <section class="text-center">
        <h2 class="display-4">Contáctanos</h2>
        <p class="lead">Estamos aquí para ayudarte. Completa el formulario y nos pondremos en contacto contigo.</p>

        <form action="enviarMensaje.jsp" method="post" class="mt-4">
            <!-- Campo Nombre -->
            <div class="form-group row justify-content-center">
                <label for="nombre" class="col-sm-2 col-form-label">Nombre:</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Escribe tu nombre" required>
                </div>
            </div>

            <!-- Campo Email -->
            <div class="form-group row justify-content-center mt-3">
                <label for="email" class="col-sm-2 col-form-label">Email:</label>
                <div class="col-sm-6">
                    <input type="email" class="form-control" id="email" name="email" placeholder="Escribe tu correo electrónico" required>
                </div>
            </div>

            <!-- Botón de envío -->
            <div class="form-group row justify-content-center mt-4">
                <div class="col-sm-6">
                    <button type="submit" class="btn btn-primary btn-lg btn-block">Enviar Mensaje</button>
                </div>
            </div>
        </form>
    </section>
</div>

<jsp:include page="footer.jsp" />

