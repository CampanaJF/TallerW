<header class="p-3 bg-dark text-white">
    <div class="container">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
          <img src="icons/favicon.ico" class="bi me-2" width="40" height="32" role="img" aria-label="CineClub"></img>
          <span class="fs-4 pe-3">CineClub</span>
        </a>

        <ul class="ps-3 nav col-12 col-lg-auto me-lg-3 mb-2 justify-content-center mb-md-0">
          <li><a href="home" class="nav-link px-2 text-secondary">Inicio</a></li>
          <li><a href="#" class="nav-link px-2 text-white nav-text">Cartelera</a></li>
          <li><a href="#" class="nav-link px-2 text-white nav-text">Goloton</a></li>
        </ul>

        
        <c:if test="${empty usuario}">
        
        <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-auto">
          <input type="search" class="form-control search form-control-dark" placeholder="Buscar..." aria-label="Buscar">
        </form>

        <div class="text-end">
        
          <a href="login"><button type="button" class="btn btn-outline-light me-2">Ingresar</button></a>
          <a href="registrarme"><button type="button" class="buttonA">Registrarme</button></a>
          
        </div>
          
        </c:if>
          
        <c:if test="${not empty usuario}">
        
        <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-auto">
          <input type="search" class="form-control searchBeeg form-control-dark" placeholder="Buscar..." aria-label="Buscar">
        </form>

        <div class="text-end">
          
          <ul class="ps-3 nav col-12 col-lg-auto me-lg-3 mb-2 justify-content-center mb-md-0">
          
	      <li><a href="perfil" class="nav-link px-2 text-white nav-text me-3">Mi Perfil</a></li>
         
          <li><a href="logout"><button type="button" class="buttonA">Salir</button></a></li>
          
          </ul>
          
        </div>
          
        </c:if>
                 
        </div>
      </div>
    
</header>