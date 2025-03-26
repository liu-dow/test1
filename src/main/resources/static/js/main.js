// Main JavaScript file for PetGear Reviews website
document.addEventListener('DOMContentLoaded', function() {
    // Elements
    const siteHeader = document.querySelector('.site-header');
    const menuToggle = document.querySelector('.menu-toggle');
    const menuClose = document.querySelector('.menu-close');
    const siteOverlay = document.querySelector('.site-overlay');
    const searchToggle = document.querySelector('.search-toggle');
    const searchContainer = document.querySelector('.search-container');
    const submenuToggles = document.querySelectorAll('.submenu-toggle');
    const menuLinks = document.querySelectorAll('.menu-item a:not(.submenu-toggle)');
    
    // Functions
    function openMenu() {
        document.body.classList.add('menu-open');
        if (menuToggle) {
            menuToggle.classList.add('menu-active');
        }
    }
    
    function closeMenu() {
        document.body.classList.remove('menu-open');
        if (menuToggle) {
            menuToggle.classList.remove('menu-active');
        }
    }
    
    function handleScroll() {
        if (window.scrollY > 50) {
            if (siteHeader) {
                siteHeader.classList.add('scrolled');
            }
        } else {
            if (siteHeader) {
                siteHeader.classList.remove('scrolled');
            }
        }
    }
    
    // Event Listeners
    if (menuToggle) {
        menuToggle.addEventListener('click', function() {
            if (document.body.classList.contains('menu-open')) {
                closeMenu();
            } else {
                openMenu();
            }
        });
    }
    
    if (menuClose) {
        menuClose.addEventListener('click', closeMenu);
    }
    
    if (siteOverlay) {
        siteOverlay.addEventListener('click', closeMenu);
    }
    
    if (submenuToggles) {
        submenuToggles.forEach(toggle => {
            toggle.addEventListener('click', function(e) {
                if (window.innerWidth < 992) {
                    e.preventDefault();
                    const parent = this.closest('.has-submenu');
                    
                    // Close all other submenus
                    document.querySelectorAll('.has-submenu.active').forEach(item => {
                        if (item !== parent) {
                            item.classList.remove('active');
                        }
                    });
                    
                    // Toggle current submenu
                    parent.classList.toggle('active');
                }
            });
        });
    }
    
    if (searchToggle && searchContainer) {
        searchToggle.addEventListener('click', function() {
            searchContainer.classList.toggle('active');
            if (searchContainer.classList.contains('active')) {
                searchContainer.style.display = 'block';
                searchContainer.querySelector('.search-input').focus();
            } else {
                setTimeout(() => {
                    searchContainer.style.display = 'none';
                }, 300);
            }
        });
    }
    
    if (menuLinks) {
        menuLinks.forEach(link => {
            link.addEventListener('click', function() {
                if (window.innerWidth < 992 && document.body.classList.contains('menu-open')) {
                    closeMenu();
                }
            });
        });
    }
    
    // Smooth scrolling for anchor links
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function(e) {
            const targetId = this.getAttribute('href');
            if (targetId === '#') return;
            
            const targetElement = document.querySelector(targetId);
            if (targetElement) {
                e.preventDefault();
                targetElement.scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });
                
                // Close menu if open
                if (window.innerWidth < 992 && document.body.classList.contains('menu-open')) {
                    closeMenu();
                }
            }
        });
    });
    
    // Handle Scroll Event
    window.addEventListener('scroll', handleScroll);
    handleScroll(); // Initialize on page load
    
    // Handle Resize Event
    function handleResize() {
        if (window.innerWidth >= 992) {
            // Reset menu state when switching to desktop
            closeMenu();
            
            // Reset search container display
            if (searchContainer) {
                searchContainer.style.display = '';
                searchContainer.classList.remove('active');
            }
        }
    }
    
    window.addEventListener('resize', handleResize);
    
    // Add animation to elements when they come into view
    const animateOnScroll = function() {
        const elements = document.querySelectorAll('.animate-on-scroll');
        
        elements.forEach(element => {
            const elementPosition = element.getBoundingClientRect().top;
            const windowHeight = window.innerHeight;
            
            if (elementPosition < windowHeight - 50) {
                element.classList.add('animated');
            }
        });
    };
    
    // Run animation check on scroll
    window.addEventListener('scroll', animateOnScroll);
    animateOnScroll(); // Run once on page load
    
    // Back to top button
    const backToTopBtn = document.querySelector('.back-to-top');
    if (backToTopBtn) {
        window.addEventListener('scroll', function() {
            if (window.scrollY > 300) {
                backToTopBtn.classList.add('show');
            } else {
                backToTopBtn.classList.remove('show');
            }
        });
        
        backToTopBtn.addEventListener('click', function(e) {
            e.preventDefault();
            window.scrollTo({
                top: 0,
                behavior: 'smooth'
            });
        });
    }
}); 