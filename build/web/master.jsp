<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="modelo.MenuItem"%>
<%
    List<MenuItem> menuItems = (List<MenuItem>) session.getAttribute("menuItems");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Menu</title>
        <%--<link rel="stylesheet" href="styles/bootstrap.min.css">--%>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <link rel="stylesheet" href="styles/master.css">

    </head>
    <body>
        <!--<header class="bg-primary text-white text-center py-3">
            <h1>Menú</h1>
        </header>-->

        <div class="bg">
            <nav class="navbar navbar-expand-lg fixed-top">

                <div class="container mt-0">

                    <a class="navbar-brand mt-0" href="master.jsp" title="Ir al panel principal" style="font-size: 150%; color:#000;"><i class="bi bi-car-front-fill" style="font-size: 300%; color:#000;"></i></a>

                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav">
                            <%
                                if (menuItems != null) {
                                    for (MenuItem item : menuItems) {
                                        if (!item.isEsSubMenu() && item.getIdMenuPadre() == 0 && item.getHref() != null) {
                                            // Elemento principal con enlace
                            %>
                            <li class="nav-item">
                                <a class="nav-link" style="color:#000;" href="<%= item.getHref()%>"><%= item.getDescripcion()%><i class="<%= item.getIcon()%>" style="font-size: 300%; color:#000;"></i></a>
                            </li>
                            <%
                            } else if (!item.isEsSubMenu() && item.getHref() == null) {
                                // Encabezado principal de submenú (sin enlace)
                            %>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" style="color:#000;" href="#" id="menu<%= item.getIdMenu()%>" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <%= item.getDescripcion()%><i class="<%= item.getIcon()%>" style="font-size: 200%; color:#000; margin-left:1rem;"></i>
                                </a>
                                <div class="dropdown-menu" aria-labelledby="menu<%= item.getIdMenu()%>">
                                    <%
                                        // Añadir elementos de submenú que pertenecen a este encabezado
                                        for (MenuItem subItem : menuItems) {
                                            if (subItem.isEsSubMenu() && subItem.getIdMenuPadre() == item.getIdMenu()) {
                                    %>
                                    <a class="dropdown-item" href="<%= subItem.getHref()%>"><%= subItem.getDescripcion()%><i class="<%= subItem.getIcon()%>" style="font-size: 200%; color:#000; margin-left:1rem;"></i></a>
                                        <%
                                                }
                                            }
                                        %>
                                </div>
                            </li>
                            <%
                                        }
                                    }
                                }
                            %>
                        </ul>
                    </div>
                </div>
                <a class="navbar-brand" href="index.jsp" title="LogOut"><i class="bi bi-box-arrow-left" style="font-size: 300%; color:#000;"></i></a>

        </div>      
    </nav>

    <jsp:include page="footer.jsp" />

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


    <%--<script src="js/jquery.min.js"></script>--%>
    <script src="js/sweetalert2.js"></script>
    <%--<script src="js/popper.min.js"></script>--%>
    <%--<script src="js/bootstrap.bundle.min.js"></script>--%>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
