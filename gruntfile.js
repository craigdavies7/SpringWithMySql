module.exports = function(grunt) {

  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),

    copy: {
      production: {
        files: [
          // fonts
		  {
		    expand: true,
			flatten: true,
			src: [
              'bower_components/bootstrap/dist/fonts/*', // glyphicons
              'bower_components/font-awesome/fonts/*' // font awesome icons
            ],
			dest: 'src/main/resources/public/fonts/'
		  }
  	    ]
      }
		},

    concat: {
      options: {
        separator: ';\n',
      },
      production: {
        src: ['bower_components/jquery/dist/jquery.js',
              'bower_components/bootstrap-sass/assets/javascripts/bootstrap.js',
              'src/main/assets/javascripts/main.js'],
        dest: 'src/main/resources/public/js/main.js'
      }
    },

    uglify: {
      production: {
        options: {
          mangle: true,
          beautify: false,
          compress: true
        },
        files: [
          {'src/main/resources/public/js/main.min.js': 'src/main/resources/public/js/main.js'},
          // minify all page specific javascript
          {
            expand: true,
            cwd: 'src/main/resources/public/js/pages/',
            src: ['*.js', '!*.min.js'],
            dest: 'src/main/resources/public/js/pages/',
            ext: '.min.js'
          }
        ]
      }
    },

    sass: {
      development: {
        options: {
          style: 'expanded',
          sourcemap: 'auto',
          loadPath: [
            "bower_components/bootstrap-sass/assets/stylesheets",
            "bower_components/font-awesome/scss"
          ]
        },
        files: {
          'src/main/resources/public/css/main.css': 'src/main/assets/stylesheets/main.scss'
        }
      },
      production: {
        options: {
          style: 'compressed',
          sourcemap: 'none',
          loadPath: [
            "bower_components/bootstrap-sass/assets/stylesheets",
            "bower_components/font-awesome/scss"
          ]
        },
        files: {
          'src/main/resources/public/css/main.min.css': 'src/main/assets/stylesheets/main.scss'
        }
      }
    }

  });

  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-copy');
  grunt.loadNpmTasks('grunt-contrib-concat');
  grunt.loadNpmTasks('grunt-contrib-sass');
  grunt.registerTask('default', ['copy:production', 'concat:production', 'uglify:production', 'sass:development', 'sass:production']);
};