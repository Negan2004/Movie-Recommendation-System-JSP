<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-4">

            <div class="card p-4 shadow">
                <h4 class="text-center mb-3">Admin Login</h4>

                <form method="post" action="adminLogin">
                    <input class="form-control mb-3" name="username" placeholder="Username" required>
                    <input type="password" class="form-control mb-3" name="password" placeholder="Password" required>
                    <button class="btn btn-dark w-100">Login</button>
                </form>

            </div>

        </div>
    </div>
</div>

</body>
</html>
