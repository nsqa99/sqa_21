<%-- 
    Document   : admin-index.jsp
    Created on : Apr 21, 2021, 2:44:52 PM
    Author     : nsqa
--%>

<%@page import="java.util.List"%>
<%@page import="model.ElecInfo"%>
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
                            data-toggle="modal"
                            data-target="#logoutModal"
                            href="#"
                            >????ng xu???t</a
                        >
                    </div>
                </li>
            </ul>
        </nav>

        <div id="wrapper">
            <!-- Sidebar -->
            <ul class="sidebar navbar-nav">

                <li class="nav-item active">
                    <a class="nav-link" href="<%=request.getContextPath()%>/admin">
                        <i class="fas fa-fw fa-chart-area"></i>
                        <span>????ng ti???n ??i???n</span></a
                    >
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/admin-config">
                        <i class="fas fa-fw fa-table"></i>
                        <span>C???u h??nh ??i???n</span></a
                    >
                </li>
            </ul>

            <div id="content-wrapper">
                <div class="container-fluid">
                    <!-- Breadcrumbs-->
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="#">????ng ti???n ??i???n</a>
                        </li>
                        <li class="breadcrumb-item active">Danh s??ch h??? gia ????nh</li>
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
                                            <th>H??? t??n</th>
                                            <th>?????a ch???</th>
                                            <th>S??? ??i???n tho???i</th>
                                            <th>Th??ng</th>
                                            <th>S??? ??i???n n??ng ti??u th??? (kWh)</th>
                                            <th>S??? ti???n c???n ????ng (?????ng)</th>
                                            <th>S??? ti???n ???? ????ng (?????ng)</th>
                                            <th>
                                                <button 
                                                    class="btn btn-warning"
                                                    data-toggle="modal"
                                                    data-target="#addModal">
                                                    <i class="fas fa-plus"></i>
                                                    &nbsp;Th??m m???i
                                                </button>
                                            </th>
                                        </tr>
                                    </thead>
                                    <%
                                        List<ElecInfo> infos = (List<ElecInfo>) session.getAttribute("infos");
                                    %>
                                    <tbody>
                                        <%
                                            if (infos != null) {
                                                for (ElecInfo info : infos) {
                                        %>
                                        <tr>
                                            <td><%=info.getId()%></td>
                                            <td><%=info.getUserFullName()%></td>
                                            <td><%=info.getUserAddr()%></td>
                                            <td><%=info.getUserPhoneNum()%></td>
                                            <td><%=info.getMonth()%></td>
                                            <td id="consumed-amount-<%=info.getUserid() + "-" + info.getMonth()%>"><%=info.getConsumedAmount()%></td>
                                            <td id="debt"><%=String.format("%,.0f", info.getPrice())%></td>
                                            <td id="paid-amount"><%=info.getPaymentStatus().equalsIgnoreCase("NP") ? 0 : String.format("%,.0f", info.getPrice())%></td>
                                            <td>
                                                <div class="d-flex justify-content-between">
                                                    <button
                                                        class="btn btn-primary mr-2"
                                                        <% if (info.getPaymentStatus().equalsIgnoreCase("p")) { %>
                                                        disabled="true"
                                                        <%}%>
                                                        data-toggle="modal"
                                                        data-target="#updateModal"
                                                        id="btn-update-<%=info.getId()%>"
                                                        data-id="<%=info.getUserid()%>"
                                                        data-fuck="<%=info.getId()%>"
                                                        data-name="<%=info.getUserFullName()%>"
                                                        data-address="<%=info.getUserAddr()%>"
                                                        data-amount="<%=info.getConsumedAmount()%>"
                                                        data-month="<%=info.getMonth()%>"
                                                        >
                                                        C???p nh???t
                                                    </button>
                                                    <button
                                                        class="btn btn-secondary"
                                                        <% if (info.getPaymentStatus().equalsIgnoreCase("p")) { %>
                                                        disabled="true"
                                                        <%}%>
                                                        id="btn-handin-<%=info.getId()%>"
                                                        data-toggle="modal"
                                                        data-target="#payModal"
                                                        data-id="<%=info.getUserid()%>"
                                                        data-fuck="<%=info.getId()%>"
                                                        data-name="<%=info.getUserFullName()%>"
                                                        data-address="<%=info.getUserAddr()%>"
                                                        data-month="<%=info.getMonth()%>"
                                                        data-amount="<%=info.getConsumedAmount()%>"
                                                        data-total="<%=String.format("%,.0f", info.getPrice())%>"
                                                        >
                                                        ????ng ti???n
                                                    </button>
                                                </div>
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
                      <span>Copyright ?? Your Website 2018</span>
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
                        <h5 class="modal-title" id="exampleModalLabel">????ng xu???t</h5>
                        <button
                            class="close"
                            type="button"
                            data-dismiss="modal"
                            aria-label="Close"
                            >
                            <span aria-hidden="true">??</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        B???n c?? ch???c ch???n mu???n ????ng xu???t?
                    </div>
                    <div class="modal-footer">
                        <button
                            class="btn btn-secondary"
                            type="button"
                            data-dismiss="modal"
                            aria-label='Cancel'
                            >
                            H???y
                        </button>
                        <a class="btn btn-primary" href="<%=request.getContextPath()%>/logout" aria-label='Logout'>????ng xu???t</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Update modal-->
        <div
            class="modal fade"
            id="updateModal"
            tabindex="-1"
            role="dialog"
            aria-labelledby="exampleModalLabel"
            aria-hidden="true"
            >
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">C???p nh???t s??? ??i???n</h5>
                        <button
                            class="close"
                            type="button"
                            data-dismiss="modal"
                            aria-label="Close"
                            >
                            <span aria-hidden="true">??</span>
                        </button>
                    </div>
                    <form action="<%=request.getContextPath()%>/admin-update" method="POST">
                        <div class="modal-body">

                            <div class="form-row">
                                <div class="form-group col-md-2">
                                    <label for="userId">ID</label>
                                    <input
                                        type="text"
                                        class="form-control"
                                        id="userId-indx"
                                        disabled
                                        />
                                    <input hidden="true" name="idUpdate" id="id-indx" />
                                </div>
                                <div class="form-group col-md-10">
                                    <label for="userFullName-indx">H??? t??n</label>
                                    <input
                                        type="text"
                                        class="form-control"
                                        id="userFullName-indx"
                                        disabled
                                        />
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="userAddr-indx">?????a ch???</label>
                                <input
                                    type="text"
                                    class="form-control"
                                    id="userAddr-indx"
                                    disabled
                                    />
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="month-indx">Th??ng</label>
                                    <input
                                        type="text"
                                        class="form-control"
                                        id="month-indx"
                                        disabled
                                        />
                                </div>
                                <div class="form-group col-md-8">
                                    <label for="consumed-indx">S??? ??i???n n??ng ti??u th??? (kWh)</label>
                                    <input
                                        type="number"
                                        min="1"
                                        name="amount"
                                        class="form-control"
                                        id="consumed-indx"
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
                                aria-label="Cancel"
                                >
                                H???y
                            </button>
                            <button type="submit" class="btn btn-primary" aria-label="Update">C???p nh???t</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- pay modal -->
    <div
        class="modal fade"
        id="payModal"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
        >
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">
                        X??c nh???n ????ng ti???n
                    </h5>
                    <button
                        class="close"
                        type="button"
                        data-dismiss="modal"
                        aria-label="Close"
                        >
                        <span aria-hidden="true">??</span>
                    </button>
                </div>
                <form action="<%=request.getContextPath()%>/admin-pay" method="POST">
                    <div class="modal-body">

                        <div class="form-row">
                            <div class="form-group col-md-2">
                                <label for="userId">ID</label>
                                <input
                                    type="text"
                                    class="form-control"
                                    id="userId"
                                    disabled
                                    />
                                <input hidden="true" name="idPay" id="idPay" />
                            </div>
                            <div class="form-group col-md-10">
                                <label for="userFullName">H??? t??n</label>
                                <input
                                    type="text"
                                    class="form-control"
                                    id="userFullName"
                                    disabled
                                    />
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="userAddr">?????a ch???</label>
                            <input
                                type="text"
                                class="form-control"
                                id="userAddr"
                                disabled
                                />
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label for="month">Th??ng</label>
                                <input
                                    type="text"
                                    class="form-control"
                                    id="month"
                                    disabled
                                    />
                            </div>
                            <div class="form-group col-md-8">
                                <label for="consumed">S??? ??i???n n??ng ti??u th??? (kWh)</label>
                                <input
                                    type="text"
                                    class="form-control"
                                    id="consumed"
                                    disabled
                                    
                                    />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="toPay">S??? ti???n c???n tr??? (?????ng)</label>
                            <input
                                type="text"
                                class="form-control"
                                id="toPay"
                                disabled
                                />
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button
                            class="btn btn-secondary"
                            type="button"
                            data-dismiss="modal"
                            aria-label="Cancel"
                            >
                            H???y
                        </button>
                        <button type="submit" class="btn btn-primary" aria-label="Handin" href="#">????ng ti???n</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- add modal -->

    <div
        class="modal fade"
        id="addModal"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
        >
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">
                        Th??m m???i th??ng tin ??i???n theo th??ng
                    </h5>
                    <button
                        class="close"
                        type="button"
                        data-dismiss="modal"
                        aria-label="Close"
                        >
                        <span aria-hidden="true">??</span>
                    </button>
                </div>
                <form action="<%=request.getContextPath()%>/admin-add-info" method="POST">
                    <div class="modal-body">
                        <div class="form-group col-md-6">
                            <label for="month">Th??ng</label>
                            <input
                                type="month"
                                class="form-control"
                                id="month-add"
                                name="monthToAdd"
                                required
                                />
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button
                            class="btn btn-secondary"
                            type="button"
                            data-dismiss="modal"
                            aria-label="Cancel"
                            >
                            H???y
                        </button>
                        <button type="submit" class="btn btn-primary" aria-label="Add">Th??m</button>
                    </div>
                </form>
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
