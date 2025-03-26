/**
 * Common JavaScript functions for PetGear Reviews website
 * This file contains shared functionality used across the site
 */

// Utility functions
const PetGear = {
    // DOM utility functions
    dom: {
        /**
         * Get element by ID with error handling
         * @param {string} id - Element ID
         * @returns {HTMLElement|null} - The element or null if not found
         */
        getById: function(id) {
            return document.getElementById(id);
        },
        
        /**
         * Get elements by selector with error handling
         * @param {string} selector - CSS selector
         * @param {HTMLElement} [parent=document] - Parent element to search within
         * @returns {NodeList} - List of matching elements
         */
        getAll: function(selector, parent = document) {
            return parent.querySelectorAll(selector);
        },
        
        /**
         * Add event listener with error handling
         * @param {HTMLElement} element - Element to add listener to
         * @param {string} event - Event name
         * @param {Function} callback - Event handler
         */
        addEvent: function(element, event, callback) {
            if (element) {
                element.addEventListener(event, callback);
            }
        }
    },
    
    // Animation utilities
    animation: {
        /**
         * Animate elements when they enter the viewport
         * @param {string} selector - CSS selector for elements to animate
         * @param {string} [animationClass='animated'] - Class to add when element is visible
         */
        animateOnScroll: function(selector = '.animate-on-scroll', animationClass = 'animated') {
            const elements = document.querySelectorAll(selector);
            
            const checkVisibility = function() {
                elements.forEach(element => {
                    const elementPosition = element.getBoundingClientRect().top;
                    const windowHeight = window.innerHeight;
                    
                    if (elementPosition < windowHeight - 50) {
                        element.classList.add(animationClass);
                    }
                });
            };
            
            // Check on scroll
            window.addEventListener('scroll', checkVisibility);
            
            // Initial check
            checkVisibility();
        }
    },
    
    // UI utilities
    ui: {
        /**
         * Initialize back to top button
         * @param {string} [selector='#backToTop'] - Button selector
         */
        initBackToTop: function(selector = '#backToTop') {
            const backToTopBtn = document.querySelector(selector);
            
            if (backToTopBtn) {
                // Show/hide button based on scroll position
                window.addEventListener('scroll', function() {
                    if (window.scrollY > 300) {
                        backToTopBtn.classList.add('show');
                    } else {
                        backToTopBtn.classList.remove('show');
                    }
                });
                
                // Scroll to top when clicked
                backToTopBtn.addEventListener('click', function(e) {
                    e.preventDefault();
                    window.scrollTo({
                        top: 0,
                        behavior: 'smooth'
                    });
                });
            }
        },
        
        /**
         * Initialize tooltips
         * @param {string} [selector='[data-tooltip]'] - Elements with tooltips
         */
        initTooltips: function(selector = '[data-tooltip]') {
            const tooltipElements = document.querySelectorAll(selector);
            
            tooltipElements.forEach(element => {
                element.addEventListener('mouseenter', function() {
                    const tooltipText = this.getAttribute('data-tooltip');
                    
                    if (tooltipText) {
                        const tooltip = document.createElement('div');
                        tooltip.className = 'tooltip';
                        tooltip.textContent = tooltipText;
                        
                        document.body.appendChild(tooltip);
                        
                        const rect = this.getBoundingClientRect();
                        tooltip.style.top = rect.bottom + window.scrollY + 10 + 'px';
                        tooltip.style.left = rect.left + window.scrollX + (rect.width / 2) - (tooltip.offsetWidth / 2) + 'px';
                        
                        setTimeout(() => tooltip.classList.add('show'), 10);
                        
                        this.addEventListener('mouseleave', function onMouseLeave() {
                            tooltip.classList.remove('show');
                            
                            setTimeout(() => {
                                document.body.removeChild(tooltip);
                            }, 300);
                            
                            this.removeEventListener('mouseleave', onMouseLeave);
                        });
                    }
                });
            });
        }
    },
    
    // Form utilities
    form: {
        /**
         * Validate form inputs
         * @param {HTMLFormElement} form - Form to validate
         * @returns {boolean} - Whether the form is valid
         */
        validate: function(form) {
            if (!form) return false;
            
            let isValid = true;
            const inputs = form.querySelectorAll('input, textarea, select');
            
            inputs.forEach(input => {
                // Clear previous error
                const errorElement = input.parentNode.querySelector('.error-message');
                if (errorElement) {
                    errorElement.remove();
                }
                
                input.classList.remove('error');
                
                // Check required fields
                if (input.hasAttribute('required') && !input.value.trim()) {
                    this.showError(input, 'This field is required');
                    isValid = false;
                }
                
                // Check email format
                if (input.type === 'email' && input.value.trim()) {
                    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                    if (!emailRegex.test(input.value.trim())) {
                        this.showError(input, 'Please enter a valid email address');
                        isValid = false;
                    }
                }
            });
            
            return isValid;
        },
        
        /**
         * Show error message for an input
         * @param {HTMLElement} input - Input element
         * @param {string} message - Error message
         */
        showError: function(input, message) {
            input.classList.add('error');
            
            const errorElement = document.createElement('div');
            errorElement.className = 'error-message';
            errorElement.textContent = message;
            
            input.parentNode.appendChild(errorElement);
        }
    }
};

// Initialize common functionality when DOM is loaded
document.addEventListener('DOMContentLoaded', function() {
    // Initialize back to top button
    PetGear.ui.initBackToTop();
    
    // Initialize animations
    PetGear.animation.animateOnScroll();
    
    // Initialize tooltips
    PetGear.ui.initTooltips();
}); 