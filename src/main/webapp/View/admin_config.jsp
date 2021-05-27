<%-- 
    Document   : admin_config.jsp
    Created on : Apr 21, 2021, 2:45:10 PM
    Author     : nsqa
--%>

<%@page import="java.util.List"%>
<%@page import="model.ElectricityPrice"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
            />
        <meta name="description" content="" />
        <meta name="author" content="" />

        <title>EVN Admin</title>

        <!-- Bootstrap core CSS-->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />

        <!-- Custom fonts for this template-->
        <link
            href="vendor/fontawesome-free/css/all.min.css"
            rel="stylesheet"
            type="text/css"
            />

        <!-- Page level plugin CSS-->
        <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet" />

        <!-- Custom styles for this template-->
        <link href="css/sb-admin.css" rel="stylesheet" />
    </head>

    <body id="page-top">
        <nav class="navbar navbar-expand navbar-dark bg-dark static-top">
            <a class="navbar-brand mr-1" href="index.html">EVN Admin</a>

            <button
                class="btn btn-link btn-sm text-white order-1 order-sm-0"
                id="sidebarToggle"
                href="#"
                >
                <i class="fas fa-bars"></i>
            </button>

            <!-- Navbar -->
            <ul class="navbar-nav ml-auto mr-0 mr-md-0 my-2 my-md-0">


                <li class="nav-item dropdown no-arrow">
                    <a
                        class="nav-link dropdown-toggle"
                        href="#"
                        id="userDropdown"
                        role="button"
                        data-toggle="dropdown"
                        aria-haspopup="true"
                        aria-expanded="false"
                        >
                        <i class="fas fa-user-circle fa-fw"></i>
                    </a>
                    <div
                        class="dropdown-menu dropdown-menu-right"
                        aria-labelledby="userDropdown"
                        >
                        <a
                            class="dropdown-item"
                            href="#"
                            data-toggle="modal"
                            data-target="#logoutModal"
                            >Đăng xuất</a
                        >
                    </div>
                </li>
            </ul>
        </nav>

        <div id="wrapper">
            <!-- Sidebar -->
            <ul class="sidebar navbar-nav">

                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/admin">
                        <i class="fas fa-fw fa-chart-area"></i>
                        <span>Đóng tiền điện</span></a
                    >
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="<%=request.getContextPath()%>/admin-config">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Cấu hình điện</span></a
                    >
                </li>
            </ul>

            <div id="content-wrapper">
                <div class="container-fluid">
                    <!-- Breadcrumbs-->
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="#">Cấu hình điện</a>
                        </li>
                        <li class="breadcrumb-item active">Danh sách cấu hình</li>
                    </ol>

                    <!-- DataTables Example -->
                    <div class="card mb-3">
                        <!-- <div class="card-header">
                          <i class="fas fa-table"></i>
                          Data Table Example</div> -->
                        <div class="card-body">
                            <div class="table-responsive">
                                <table
                                    class="table table-bordered"
                                    id="dataTable"
                                    width="100%"
                                    cellspacing="0"
                                    >
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Địa điểm cấu hình</th>
                                            <th>Bậc 1</th>
                                            <th>Bậc 2</th>
                                            <th>Bậc 3</th>
                                            <th>Bậc 4</th>
                                            <th>Bậc 5</th>
                                            <th>Bậc 6</th>
                                            <th>
                                                <button 
                                                    class="btn btn-warning"
                                                    data-toggle="modal"
                                                    data-target="#configAddModal">
                                                    <i class="fas fa-plus"></i>
                                                    &nbsp;Thêm mới
                                                </button>
                                            </th>
                                        </tr>
                                    </thead>
                                    <%
                                        List<ElectricityPrice> prices = (List<ElectricityPrice>) session.getAttribute("prices");
                                    %>
                                    <tbody>
                                        <%
                                            if (prices != null) {
                                                for (ElectricityPrice price : prices) {
                                        %>
                                        <tr>
                                            <td><%=price.getId()%></td>
                                            <td><%=price.getArea()%></td>
                                            <% for (int level : price.getLevels()) {%>
                                            <td><%=level%></td>

                                            <% }%>
                                            <td>
                                                <button
                                                    class="btn btn-primary mr-2"
                                                    data-toggle="modal"
                                                    data-target="#configModal"
                                                    data-idP="<%=price.getId()%>"
                                                    data-area="<%=price.getId() == 1 ? "Thành phố" : "Nông thôn"%>"
                                                    data-levels="<%=price.getLevels()%>"
                                                    >
                                                    Cập nhật
                                                </button>
                                            </td>
                                        </tr>

                                        <%  }
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- <div class="card-footer small text-muted">
                          Updated yesterday at 11:59 PM
                        </div> -->
                    </div>
                </div>
                <!-- /.container-fluid -->

                <!-- Sticky Footer -->
                <!-- <footer class="sticky-footer">
                  <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                      <span>Copyright © Your Website 2018</span>
                    </div>
                  </div>
                </footer> -->
            </div>
            <!-- /.content-wrapper -->
        </div>
        <!-- /#wrapper -->

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Logout Modal-->
        <div
            class="modal fade"
            id="logoutModal"
            tabindex="-1"
            role="dialog"
            aria-labelledby="exampleModalLabel"
            aria-hidden="true"
            >
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Đăng xuất</h5>
                        <button
                            class="close"
                            type="button"
                            data-dismiss="modal"
                            aria-label="Close"
                            >
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Bạn có chắc chắn muốn đăng xuất?
                    </div>
                    <div class="modal-footer">
                        <button
                            class="btn btn-secondary"
                            type="button"
                            data-dismiss="modal"
                            >
                            Hủy
                        </button>
                        <a class="btn btn-primary" href="<%=request.getContextPath()%>/logout">Đăng xuất</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Add modal-->
        <div
            class="modal fade"
            id="configAddModal"
            tabindex="-1"
            role="dialog"
            aria-labelledby="exampleModalLabel"
            aria-hidden="true"
            >
            <div class="modal-dialog" role="document">mớimới
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Thêm  cấu hình điện</h5>
                        <button
                            class="close"
                            type="button"
                            data-dismiss="modal"
                            aria-label="Close"
                            >
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="<%=request.getContextPath()%>/admin-config-add" method="POST">
                            <div class="form-row">

                                <div class="form-group col-md-10">
                                    <label for="configAddr">Địa điểm</label>
                                    <input
                                        type="text"
                                        class="form-control"
                                        name="area_add"
                                        required
                                        />
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="level1">Bậc 1</label>
                                    <input
                                        type="number"
                                        min="1"
                                        class="form-control"
                                        name="level_1_add"
                                        required
                                        />
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="level2">Bậc 2</label>
                                    <input
                                        type="number"
                                        min="1"
                                        class="form-control"
                                        name="level_2_add"
                                        required
                                        />
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="level3">Bậc 3</label>
                                    <input
                                        type="number"
                                        min="1"
                                        class="form-control"
                                        name="level_3_add"
                                        required    
                                        />
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="level4">Bậc 4</label>
                                    <input
                                        type="number"
                                        min="1"
                                        class="form-control"
                                        name="level_4_add"
                                        required
                                        />
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="level5">Bậc 5</label>
                                    <input
                                        type="number"
                                        min="1"
                                        class="form-control"
                                        name="level_5_add"
                                        required
                                        />
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="level6">Bậc 6</label>
                                    <input
                                        type="number"
                                        min="1"
                                        class="form-control"
                                        name="level_6_add"
                                        required
                                        />
                                </div>
                            </div>

                    </div>
                    <div class="modal-footer">
                        <button
                            class="btn btn-secondary"
                            type="button"
                            data-dismiss="modal"
                            >
                            Hủy
                        </button>
                        <button class="btn btn-primary" href="index.html">Thêm</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Update modal-->
        <div
            class="modal fade"
            id="configModal"
            tabindex="-1"
            role="dialog"
            aria-labelledby="exampleModalLabel"
            aria-hidden="true"
            >
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Cập nhật cấu hình điện</h5>
                        <button
                            class="close"
                            type="button"
                            data-dismiss="modal"
                            aria-label="Close"
                            >
                            <span aria-hidden="true">×</span>
                        </button>
                    </div> 
                    <form action="<%=request.getContextPath()%>/admin-config-update" method="POST">
                        <div class="modal-body">

                            <div class="form-row">
                                <div class="form-group col-md-2">
                                    <label for="idConfig">ID</label>
                                    <input
                                        type="text"
                                        class="form-control"
                                        id="idConfig"
                                        name="retard"
                                        disabled
                                        />
                                </div>
                                <div class="form-group col-md-10">
                                    <label for="configAddr">Địa điểm</label>
                                    <input
                                        type="text"
                                        class="form-control"
                                        id="configAddr"
                                        name="area_update"
                                        required
                                        />
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="level1">Bậc 1</label>
                                    <input
                                        type="number"
                                        min="0"
                                        class="form-control"
                                        id="level1"
                                        name="level_1"
                                        required
                                        />
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="level2">Bậc 2</label>
                                    <input
                                        type="number"
                                        min="0"
                                        class="form-control"
                                        id="level2"
                                        name="level_2"
                                        required
                                        />
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="level3">Bậc 3</label>
                                    <input
                                        type="number"
                                        min="0"
                                        class="form-control"
                                        id="level3"
                                        name="level_3"
                                        required    
                                        />
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="level4">Bậc 4</label>
                                    <input
                                        type="number"
                                        min="0"
                                        class="form-control"
                                        id="level4"
                                        name="level_4"
                                        required
                                        />
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="level5">Bậc 5</label>
                                    <input
                                        type="number"
                                        min="0"
                                        class="form-control"
                                        id="level5"
                                        name="level_5"
                                        required
                                        />
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="level6">Bậc 6</label>
                                    <input
                                        type="number"
                                        min="0"
                                        class="form-control"
                                        id="level6"
                                        name="level_6"
                                        required
                                        />
                                </div>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button
                                class="btn btn-secondary"
                                type="button"
                                data-dismiss="modal"
                                >
                                Hủy
                            </button>
                            <button type="submit" class="btn btn-primary">Cập nhật</button>

                        </div>
                    </form>
                </div>
            </div>
        </div>


    </div>


    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Page level plugin JavaScript-->
    <script src="vendor/datatables/jquery.dataTables.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin.js"></script>

    <!-- Demo scripts for this page-->
    <script src="js/demo/datatables-demo.js"></script>
    <script src="js/demo/chart-area-demo.js"></script>
</body>
</html>
