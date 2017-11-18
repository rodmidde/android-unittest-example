file = Workshop

html:
	pandoc $(file).md --filter include_file.py -o $(file).html --toc --from markdown --number-sections --css ~/.local/share/pandoc.css --self-contained

pdf:
	pandoc --toc $(file).md --filter include_file.py -o $(file).pdf --from markdown --template eisvogel --number-sections 


slides:
	pandoc -t revealjs -s $(file).md -o $(file)-slides.html -V theme=ica-onderwijs -V controls=false --filter include_file.py

all: html pdf slides
	
