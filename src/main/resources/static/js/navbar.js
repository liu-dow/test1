document.addEventListener('DOMContentLoaded', function() {
    // Elements
    const mobileToggle = document.querySelector('.site-mobile-toggle');
    const navMenu = document.querySelector('.site-navigation-menu');
    // Mobile menu toggle
    if (mobileToggle) {
        mobileToggle.addEventListener('click', function() {
            this.classList.toggle('site-toggle-active');
            navMenu.classList.toggle('site-menu-active');
        });
    }

    // Active link highlighting
    const navLinks = document.querySelectorAll('.site-nav-link');
    const currentPath = window.location.pathname;

    navLinks.forEach(link => {
        const linkPath = link.getAttribute('href');

        // Simple path matching - can be enhanced for more complex routing
        if (linkPath === currentPath ||
            (currentPath === '/' && linkPath === 'index.html') ||
            (linkPath === '#' && currentPath === '/')) {
            link.classList.add('site-nav-active');
        } else {
            link.classList.remove('site-nav-active');
        }
    });

    // Accessibility improvements
    const focusableElements = document.querySelectorAll('a, button, input');

    focusableElements.forEach(element => {
        element.addEventListener('focus', function() {
            this.classList.add('site-focus-visible');
        });

        element.addEventListener('blur', function() {
            this.classList.remove('site-focus-visible');
        });
    });
});