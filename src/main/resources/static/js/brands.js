// Script for Pet Tracker Brands Website

document.addEventListener('DOMContentLoaded', function() {
    // Smooth scrolling for anchor links
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function(e) {
            e.preventDefault();
            document.querySelector(this.getAttribute('href')).scrollIntoView({
                behavior: 'smooth'
            });
        });
    });

    // Mobile navigation toggle
    const createMobileMenu = () => {
        const header = document.querySelector('header');
        const nav = document.querySelector('nav');

        // Only create mobile menu if it doesn't exist yet
        if (!document.querySelector('.mobile-menu-toggle')) {
            const mobileMenuToggle = document.createElement('div');
            mobileMenuToggle.classList.add('mobile-menu-toggle');
            mobileMenuToggle.innerHTML = '<span></span><span></span><span></span>';
            header.querySelector('.container').insertBefore(mobileMenuToggle, nav);

            mobileMenuToggle.addEventListener('click', function() {
                this.classList.toggle('active');
                nav.classList.toggle('active');
            });
        }
    };

    // Check if we need the mobile menu
    const checkMobileMenu = () => {
        if (window.innerWidth <= 768) {
            createMobileMenu();
        }
    };

    // Initial check
    checkMobileMenu();

    // Check on resize
    window.addEventListener('resize', checkMobileMenu);

    // Timeline animation
    const animateTimeline = () => {
        const timelineItems = document.querySelectorAll('.timeline-item');

        if (timelineItems.length === 0) return;

        const observer = new IntersectionObserver((entries) => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    entry.target.classList.add('animate');
                    observer.unobserve(entry.target);
                }
            });
        }, {
            threshold: 0.2
        });

        timelineItems.forEach(item => {
            observer.observe(item);
        });
    };

    // Initialize timeline animation
    animateTimeline();

    // Add CSS for timeline animation
    const style = document.createElement('style');
    style.textContent = `
        .timeline-item {
            opacity: 0;
            transform: translateY(50px);
            transition: opacity 0.5s ease, transform 0.5s ease;
        }
        
        .timeline-item.animate {
            opacity: 1;
            transform: translateY(0);
        }
        
        .timeline-item.left {
            transition-delay: 0.2s;
        }
        
        .timeline-item.right {
            transition-delay: 0.4s;
        }
        
        .mobile-menu-toggle {
            display: none;
            flex-direction: column;
            justify-content: space-between;
            width: 30px;
            height: 21px;
            cursor: pointer;
        }
        
        .mobile-menu-toggle span {
            height: 3px;
            width: 100%;
            background-color: #0066cc;
            border-radius: 3px;
            transition: all 0.3s ease;
        }
        
        .mobile-menu-toggle.active span:nth-child(1) {
            transform: translateY(9px) rotate(45deg);
        }
        
        .mobile-menu-toggle.active span:nth-child(2) {
            opacity: 0;
        }
        
        .mobile-menu-toggle.active span:nth-child(3) {
            transform: translateY(-9px) rotate(-45deg);
        }
        
        @media (max-width: 768px) {
            .mobile-menu-toggle {
                display: flex;
            }
            
            nav {
                display: none;
                width: 100%;
                margin-top: 20px;
            }
            
            nav.active {
                display: block;
            }
            
            nav ul {
                flex-direction: column;
                align-items: center;
            }
            
            nav ul li {
                margin: 10px 0;
            }
        }
    `;
    document.head.appendChild(style);

    // Create image directory if it doesn't exist
    const createImageDirectory = () => {
        // This would typically be handled server-side
        console.log('Image directory would be created server-side');
    };

    // Initialize image directory
    createImageDirectory();

    // SEO enhancements
    const enhanceSEO = () => {
        // Add structured data for better search engine understanding
        const structuredData = {
            "@context": "https://schema.org",
            "@type": "Article",
            "headline": "Pet Tracker Brands: History & Development",
            "description": "Discover the history and development of leading pet tracker brands: Pawfit, Tractive, and PitPat.",
            "image": "images/hero-bg.jpg",
            "author": {
                "@type": "Organization",
                "name": "Pet Tracker Brands"
            },
            "publisher": {
                "@type": "Organization",
                "name": "Pet Tracker Brands",
                "logo": {
                    "@type": "ImageObject",
                    "url": "images/logo.png"
                }
            },
            "datePublished": new Date().toISOString()
        };

        const script = document.createElement('script');
        script.type = 'application/ld+json';
        script.text = JSON.stringify(structuredData);
        document.head.appendChild(script);
    };

    // Initialize SEO enhancements
    enhanceSEO();
});