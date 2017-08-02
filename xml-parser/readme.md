xml to html

== approach ===
1. scan body of the input jats xml
2. create a html template where different part of fulltext html correspond to different xpath in the body.
3. extract the contents in the diff part of the template. - xslt operation and fill the headings.
4. refine step 3 - is xml content has any possible html markup like sup, sub, etc.. think about how to get these markups AS WE SCAN.
5. target one heading of the template as first phase.
6. is there any pattern to re-use? similar xpath (or html elements) can be reused in the more than one heading content. -- possible recursion?
6. once html elements are coming in the heading content , REPEAT for other headings.
7. can we find list of headings dynamically at front - what is the xpath for a heading? this list will give u got0 section feature.
8. create a generic div for heading and content .
9. step 8 will help linking heading to content -- useful for goto section feature.
10. think about media objects...
11. styling etc??


===== implementation ===
1. AS WE SCAN -- read xml node by node --> process --> write to a file.  repeat till end of the body of the xml.
2. resuse xmlparser service . node -> value.
3. think about getting xhtml value out the node - help creating html from the start?



#Exception handling
1. Create a base exception class extending Exception
2. Known exceptions ?
	
	a) UnsupportedDocType
	
	b) XMLParser --> wrapper class to capture exception coming from document, node.... processing
	
	c) ValidationException
	
	d) 
3. Exception codes and messages
4. handling Connection exception with other micro services
5. Capture existence of different parts of XML (front, body,  back) and log properly./ email 
6. Configure accept/reject criteria based on 5.
 
 
# Maven Profiling with spring boot
 Steps: 
 
 1) Add profiles and activation criteria in pom.xml
 
 2) Add resources to filter and filter in pom.xml
 
 3) Create env specific property files (filter property files)
 
 4) update application properties to include filtered properties... eg... 
         input.jats.xml.path =@input.jats.xml.path1@

