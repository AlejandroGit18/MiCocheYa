<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    String title = "Inicio";
%>

<jsp:include page="master.jsp" />

<div class="container mt-5">
    <section class="text-center">
        <h2 class="display-4">Bienvenido a Mi Aplicación Web</h2>
        <p class="lead">Esta es la página de inicio.</p>

        <!-- Botón para explorar más -->
        <a href="contact.jsp" class="btn btn-primary btn-lg mt-3">Explorar más</a>
    </section>
</div>

<jsp:include page="footer.jsp" />

