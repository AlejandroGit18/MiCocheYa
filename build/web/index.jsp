<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="styles/login.css">
</head>
<body>
        <div class="bg"></div> <!-- Aquí se ha agregado el div para el fondo -->
    <div class="wrapper d-flex flex-column justify-content-center align-items-center vh-50">
        <div class="logo mb-3">
            <img src="images/LoginPicture.jpg" alt="Login" width="100">
        </div>
        <div class="text-center mt-4 name">
            <h1>MiCocheYa</h1>
        </div>

                <form action="sr_login" method="post" autocomplete="off">
                    <div class="form-group">
                        <span class="far fa-user p-2"></span>
                        <label for="username">Username</label>
                        <input type="text" class="form-control" id="username" name="username" required>
                    </div>
                    <div class="form-group">
                        <span class="fas fa-key p-2"></span>
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>
                    <button type="submit" class="btn btn-outline-info w-100 mt-3">Iniciar Sesión</button>
                </form>
        </div>
        
    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
