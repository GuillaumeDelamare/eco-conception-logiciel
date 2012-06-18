%=============================================================================
% Classe report-rd-info (basée sur la classe standard ``report'').
% Rapport de travail de recherche et développement du département d'informatique de Polytech'Nantes
% Auteur : José Martinez (originellement basé sur le rapport de recherche du LINA de Frédéric Goualard)
%=============================================================================
\def\REPORT@RD@INFO@Version{2.0}
\NeedsTeXFormat{LaTeX2e}[1995/12/01]
\ProvidesClass{report-rd-info}[2010/09/03 report-rd-info version \REPORT@RD@INFO@Version]
\typeout{(c) 2008-2010 École polytechnique de l'université de Nantes}
\typeout{ }

%>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Gestion des options =
% Déclaration des options possibles
\newif\if@inEnglish
\DeclareOption{english}{\@inEnglishtrue}
\DeclareOption{francais}{\@inEnglishfalse}

\newif\if@inDraftMode
\DeclareOption{draft}{\@inDraftModetrue}
\DeclareOption{final}{\@inDraftModefalse}

\newif\if@withVersion
\DeclareOption{version}{\@withVersiontrue}
\DeclareOption{noversion}{\@withVersionfalse}

\DeclareOption{10pt}{\gdef\@pt@size{10pt}}
\DeclareOption{11pt}{\gdef\@pt@size{11pt}}
\DeclareOption{12pt}{\gdef\@pt@size{12pt}}

\newif\ifscreen
\screenfalse
\DeclareOption{screen}{\screentrue}

\ExecuteOptions{version,francais,final,12pt}
\ProcessOptions\relax

%<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fin gestion des options =

%>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Chargement de paquetages additionnels =

\if@inDraftMode
   \ifscreen
      \PassOptionsToClass{\@pt@size,oneside,twocolumn,draft}{report}
   \else
      \PassOptionsToClass{\@pt@size,twoside,draft}{report}
   \fi
\else
   \ifscreen
      \PassOptionsToClass{\@pt@size,oneside,twocolumn,final}{report}
   \else
      \PassOptionsToClass{\@pt@size,twoside,final}{report}
   \fi
\fi
\LoadClass{report}

% Attention : ne pas déplacer l'appel pour charger 'color'
\RequirePackage{color}
\definecolor{ch@ptercolor}{rgb}{0.85,0.0,0.0}
\definecolor{r@tiomaxcolor}{rgb}{0.5,0.5,0.5}
\RequirePackage{graphicx}
\RequirePackage{times}
\ifscreen
   \RequirePackage[screen, columnsep=2.5em, margin=1.5cm, rmargin=2.5cm]{geometry}
\else
   \RequirePackage{a4wide}
\fi
\RequirePackage{ifthen}
%\RequirePackage{fancyhdr}
\RequirePackage[bookmarks,%
                colorlinks,%
                urlcolor=blue,%
                citecolor=blue,%
                linkcolor=blue,%
                hyperfigures,%
                pdfpagescrop={0 0 595 890},%
                pagebackref,%
                pdfcreator=LaTeX,%
                breaklinks=true,%
                pdfpagelayout=SinglePage,%
                bookmarksopen=true,%
                bookmarksopenlevel=1]{hyperref}

\RequirePackage[english,francais]{babel}
\if@inEnglish
\AtBeginDocument{%
  \selectlanguage{english}
}
\else
\AtBeginDocument{%
  \selectlanguage{francais}
}
\fi

\RequirePackage{multirow}
\RequirePackage{calc}

%<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< fin chargement de paquetages additionnels =


\newlength{\titlewidth}
\setlength{\titlewidth}{16cm}

%>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Localisation
\if@inEnglish
	\newcommand{\acknowledgementsname}{Acknowledgements}
	\newcommand{\rapportname}{Polytechnic School of the University of Nantes\par Computer Science Department\\[2em] Research and Development Report}
	\newcommand{\supervisedname}{supervised by}
	\AtBeginDocument{%
		\def\proofname{Proof}
		\def\today{\ifcase\month\or
		January\or February\or March\or April\or May\or June\or
		July\or August\or September\or October\or November\or December\fi
		~\number\year}
		\def\abstractname{Abstract}
		\newcommand{\surname}[1]{#1}
		\renewcommand{\refname}{References}
		\newcommand{\categoryname}{Categories and Subject Descriptors:}
		\newcommand{\termsname}{General Terms:}
		\newcommand{\keywordsname}{Additional Key Words and Phrases:}}
		
	\newcommand{\BibNameFmt}{\rm}
	\newcommand{\EndBibNameFmt}{\rm}
	\newcommand{\andMacro}{and}
	\newcommand{\editorsMacro}{eds.}
	\newcommand{\editorMacro}{ed.}
	\newcommand{\editionMacro}{edition}
	\newcommand{\chapterMacro}{chapter}
	\newcommand{\trMacro}{Technical report}
	\newcommand{\ofMacro}{of}
	\newcommand{\thesisMacro}{Doctoral thesis}
	\newcommand{\toappearMacro}{To appear}
	
	\newcommand{\janMacro}{January }
	\newcommand{\febMacro}{February }
	\newcommand{\marMacro}{March }
	\newcommand{\aprMacro}{April }
	\newcommand{\mayMacro}{May }
	\newcommand{\junMacro}{June }
	\newcommand{\julMacro}{July }
	\newcommand{\augMacro}{August }
	\newcommand{\sepMacro}{September }
	\newcommand{\octMacro}{October }
	\newcommand{\novMacro}{November }
	\newcommand{\decMacro}{December }
		
	\newcommand{\WeeklyReport}{Weekly Report \#}
	\newcommand{\WeeklyReportFrom}{From}
	\newcommand{\WeeklyReportTo}{to}
	\newcommand{\WeeklyAuthorWork}{Working time of}
	\newcommand{\WeeklyWorkDone}{Work done}
	\newcommand{\WeeklyWorkNotDone}{Work not yet done}
	\newcommand{\WeeklyExchange}{Communication with the client}
	\newcommand{\WeeklyNextPlanning}{Next week planning}
\else
	\newcommand{\acknowledgementsname}{Remerciements}
	\def\proofname{D\'emonstration}
	\newcommand{\rapportname}{\'Ecole polytechnique de l'universit\'e de Nantes\par D\'epartement d'informatique\\[2em] Rapport de recherche et d\'eveloppement}
	\newcommand{\supervisedname}{encadr\'e par}
	
	\AtBeginDocument{%
	\def\today{\ifcase\month\or
	 janvier\or f\'evrier\or mars\or avril\or mai\or juin\or
	 juillet\or août\or septembre\or octobre\or novembre\or d\'ecembre\fi
	 ~\number\year}
	\def\abstractname{R\'esum\'e}
	\renewcommand{\refname}{R\'ef\'erences}
	\newcommand{\surname}[1]{\textsc{#1}}
	\newcommand{\categoryname}{Cat\'egories et descripteurs de sujets~:}
	\newcommand{\termsname}{Termes g\'en\'eraux~:}
	\newcommand{\keywordsname}{Mots-cl\'es additionnels et phrases~:}}
	
	\newcommand{\BibNameFmt}{\sc}
	\newcommand{\EndBibNameFmt}{\rm}
	\newcommand{\andMacro}{et}
	\newcommand{\editorsMacro}{r\'eds.}
	\newcommand{\editorMacro}{r\'ed.}
	\newcommand{\editionMacro}{\'edition}
	\newcommand{\chapterMacro}{chapitre}
	\newcommand{\trMacro}{Rapport technique}
	\newcommand{\ofMacro}{de}
	\newcommand{\thesisMacro}{Th\`ese de doctorat}
	\newcommand{\toappearMacro}{\`A para\^\i{}tre}
	
	\newcommand{\janMacro}{janvier }
	\newcommand{\febMacro}{f\'evrier }
	\newcommand{\marMacro}{mars }
	\newcommand{\aprMacro}{avril }
	\newcommand{\mayMacro}{mai }
	\newcommand{\junMacro}{juin }
	\newcommand{\julMacro}{juillet }
	\newcommand{\augMacro}{ao\^ut }
	\newcommand{\sepMacro}{septembre }
	\newcommand{\octMacro}{octobre }
	\newcommand{\novMacro}{novembre }
	\newcommand{\decMacro}{d\'ecembre }
	
	\newcommand{\WeeklyReport}{Fiche de suivi de la semaine~}
	\newcommand{\WeeklyReportFrom}{du}
	\newcommand{\WeeklyReportTo}{au}
	\newcommand{\WeeklyAuthorWork}{Temps de travail de}
	\newcommand{\WeeklyWorkDone}{Travail effectu\'e}
	\newcommand{\WeeklyWorkNotDone}{Travail non effectu\'e}
	\newcommand{\WeeklyExchange}{\'Echanges avec le commanditaire}
	\newcommand{\WeeklyNextPlanning}{Planification pour la semaine prochaine}
\fi
%<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fin customisations suivant la langue =

\def\@savetitle{+}
\def\@title{\ClassError{report-rd-info}
                       {Pas de titre : Indiquez un titre avec la macro \string\title}
                       {}}
\def\title#1{\gdef\@title{#1}%
  \if\@savetitle+ \gdef\@savetitle{#1}\fi}

\def\@authorA{\ClassError{report-rd-info}
                         {Pas d'auteur : Indiquez au moins un auteur avec la macro \string\authorA}
                         {}}
\newcommand{\authorA}[2]{\gdef\@authorAfirstname{#1}\gdef\@authorAlastname{#2}}

\def\@authorBfirstname{+}
\def\@authorBlastname{+}
\newcommand{\authorB}[2]{\gdef\@authorBfirstname{#1}\gdef\@authorBlastname{#2}}

\def\@supervisor{\ClassError{report-rd-info}
                            {Pas d'encadrant : Indiquez l'encadrant sous la responsabilit\'e duquel est publi\'e le rapport avec la macro \string\supervisor}
                            {}}
\newcommand{\supervisor}[2]{\gdef\@supervisorfirstname{#1}\gdef\@supervisorlastname{#2}}

%>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Gestion du cartouche de version =
\if@withVersion
  \AtBeginDocument{%
  \def\timenow{%
    \@tempcnta=\time \divide\@tempcnta by 60 \number\@tempcnta:\multiply
    \@tempcnta by 60 \@tempcntb=\time \advance\@tempcntb by -\@tempcnta
    \ifnum\@tempcntb <10 0\number\@tempcntb\else\number\@tempcntb\fi}
  \def\idline{%
    \hfil\tiny%
    \textbf{\jobname.tex}~--~{\def\\{}\@savetitle}~--~\number%
    \day/\number\month/%
    \number\year~--~\timenow}}
\fi % of @withVersion

%<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fin gestion du cartouche de version =


%>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Champs d'identification =

\def\@theme{\ClassError{report-rd-info}
                       {Pas de theme : Indiquez le th\`eme du rapport avec la macro \string\theme}
                       {}}
\newcommand{\theme}[1]{\gdef\@theme{#1}}

\def\@subtitle{+}
\newcommand{\subtitle}[1]{\gdef\@subtitle{#1}}

\def\@institution{\ClassError{report-rd-info}
                             {Pas d'institution : Indiquez l'institution d'accueil avec la macro \string\institution avec LINA ou IRCCyN (si un autre organisme est fourni, il faut aussi fournir un logo avec la macro \string\logoinstitution)}
                             {}}
\newcommand{\institution}[1]{\gdef\@institution{#1}}

\def\@logoinstitution{+}
\newcommand{\logoinstitution}[1]{\gdef\@logoinstitution{#1}}

%<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fin champs d'identification =

\renewcommand{\maketitle}{%
  \begin{titlepage}
    \centering
      {\Large\bfseries\textsc{\rapportname}\par}

      \vspace*{1cm}

      {\usefont{T1}{ptm}{b}{n}\fontsize{35pt}{37pt}\selectfont\@title\par}
      \if\@subtitle+
      \else
        \vspace{.4cm}
        {\usefont{T1}{ptm}{b}{sl}\fontsize{22pt}{23pt}\selectfont\@subtitle\par}
      \fi

      \vspace*{1cm}

      {\LARGE\bfseries\@authorAfirstname\ \textsc{\@authorAlastname}\if\@authorBfirstname+\else\ \&\ \@authorBfirstname\ \textsc{\@authorBlastname}\fi\par}

      \vspace*{1cm}

      {\huge\bfseries \@date\par}

      \vfill

      {\Large \supervisedname\ \@supervisorfirstname\ \textsc{\@supervisorlastname}\par}

      \vspace*{1em}

      \ifthenelse{\equal{\@institution}{}}
        {% then
        }
        {% else
        \Large ---~\@theme~---\par
        }

      \vspace*{1em}

      \ifthenelse{\equal{\@institution}{LINA}}
        {% then
        \Large \textsc{Laboratoire d'Informatique de Nantes-Atlantique}\par
        }
      {% elsif
      \ifthenelse{\equal{\@institution}{IRCCyN}}
        {% then
        \Large \textsc{Institut de Recherche en Communications et en Cybernétique de Nantes}\par
        }
      {% elsif
      \ifthenelse{\equal{\@institution}{}}
        {% then
        }
        {% else
        \Large \textsc{\@institution}\par
        }}}

      \vspace*{1cm}

      \ifthenelse{\equal{\@institution}{LINA}}
        {% then
        \includegraphics[height=2.5cm]{logos/LINA}
        }
      {% elsif
      \ifthenelse{\equal{\@institution}{IRCCyN}}
        {% then
        \includegraphics[height=2.5cm]{logos/IRCCyN}
        }
      {% elsif
      \ifthenelse{\equal{\@institution}{}}
        {% then
        }
        {% else
        \includegraphics[height=2.5cm]{\@logoinstitution}
        }}}

      \vfill

      \framebox{
      \begin{tabular}{cc}
        \includegraphics[height=2cm]{logos/UnivNantes}
        &
        \includegraphics[height=2cm]{logos/PolytechNantes}
      \end{tabular}
      }
  \end{titlepage}

	%% Deuxième page : texte sur les droits d'auteur
	
	
	\thispagestyle{empty}

	~
		
	\vfill
	\noindent\textbf{Avertissement}
	
	Toute reproduction, même partielle, par quelque procédé que ce soit, est interdite sans autorisation préalable.
	
	Une copie par xérographie, photographie, photocopie, film, support magnétique ou autre, constitue une contrefaçon passible des peines prévues par la loi.
	
	\clearpage
  % Troisième page : rappel de titre + auteurs + résumé
  %                  + classification + mots-clés
   \begin{center}
     {\def\\{\par\vspace{.3em}}\huge\selectfont\bfseries\@title}\par
     \if\@subtitle+
     \else
       \vspace{.4cm}
       {\Large\bfseries\@subtitle}\par
     \fi
     \vspace{1cm}
     {\def\inst##1{}\@authorAfirstname\ \textsc{\@authorAlastname}\if\@authorBfirstname+\else\ \&\ \@authorBfirstname\ \textsc{\@authorBlastname}\fi}\par
   \end{center}
  \vspace{2cm}
  \unvcopy\@abstractBoxGlobal\par
  \vspace{1cm}
  \unvcopy\@classificationBoxGlobal
  \cleardoublepage
%  \pagestyle{fancy}
%  \lhead[]{}
%  \rhead[]{}
%  \lfoot[]{}
%  \rfoot[]{}
%  \cfoot[]{\thepage}

%  \lhead[\thepage]{}
%  \rhead[]{\thepage}
%  \lfoot[]{}
%  \rfoot[]{}
%  \cfoot[]{}
} % \maketitle

%>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Quatrième de couverture =

%<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Fin quatrième de couverture =

\newsavebox{\@abstractBox}
\newbox{\@abstractBoxGlobal}
\renewenvironment{abstract}{%
  \begin{lrbox}{\@abstractBox}%
    \begin{minipage}{\columnwidth}
    \begin{center}
      \bfseries \abstractname
    \end{center}
    }{\end{minipage}\end{lrbox}%
    \global\setbox\@abstractBoxGlobal=\vbox{\copy\@abstractBox}}

% Catégories ACM
\newsavebox{\@classificationBox}
\newbox{\@classificationBoxGlobal}
\newenvironment{classification}{%
  \begin{lrbox}{\@classificationBox}%
    \begin{minipage}{\columnwidth}}{%
      % La partie ci-dessous est reprise de esub2acm.
            \ifx\@categories\@empty \else
                \vskip 4\p@
                \def\and{\unskip{\rm;} }%
                \categoryname\ \@categories\par
            \fi
            \ifx\@terms\@empty\else
                \vskip 4\p@
                \termsname\ \ignorespaces
                \@terms
                \par
            \fi
            \ifx\@keywords\@empty\else
                \vskip 4\p@
                \keywordsname\ \ignorespaces
                \@keywords
                \par
            \fi
      \end{minipage}\end{lrbox}
    \global\setbox\@classificationBoxGlobal=\vbox{\copy\@classificationBox}}
% Ci-dessous: code repris de esub2acm.cls
\def\terms{\gdef\@terms}
\let\@terms\@empty
\def\keywords{\gdef\@keywords}
\let\@keywords\@empty
\def\category#1#2#3{%
    \@ifnextchar [{\@category{#1}{#2}{#3}}{\@category{#1}{#2}{#3}[]}%
}
\def\@category#1#2#3[#4]{%
    \edef\@tempa{\ifx \@categories\@empty \else ; \fi}%
    \begingroup
        \def\protect{\noexpand\protect\noexpand}%
        \let\and\relax
        \xdef\@categories{%
            \@categories
            \@tempa
            #1 [{\bf #2}]%
            \if!#4!%
                \if!#3!\else : #3\fi
            \else
                :\space
                \if!#3!\else #3\kern\z@---\hskip\z@\fi
                {\it #4}%
            \fi
        }%
    \endgroup
}

\let\@categories\@empty


\def\cleardoublepage{\clearpage\thispagestyle{empty}%
  \ifodd\c@page\else
    \hbox{}\newpage\thispagestyle{empty}\if@twocolumn\hbox{}\newpage\fi\fi}

% Redefinition de l'en-tete d'un chapitre.
\def\@makechapterhead#1{%
  \vspace*{50\p@}%
  {\parindent \z@ \raggedleft \normalfont
    \ifnum \c@secnumdepth >\m@ne
        \vskip 20\p@
        \textcolor{ch@ptercolor}{\hrulefill}
        \raisebox{-13\p@}{\Huge\bfseries \fcolorbox{white}{ch@ptercolor}{\scalebox{4}{\textcolor{white}{\thechapter}}}} % \@chapapp\space \thechapter
        \par\nobreak
        \vskip 20\p@
    \fi
    \interlinepenalty\@M
    \raggedright
    \Huge \bfseries \textcolor{ch@ptercolor}{#1}\par\nobreak
    \vskip 40\p@
  }}
  
% Environnement de remerciements.
\newenvironment{acknowledgements}{\section*{\acknowledgementsname}}{\par}

\bibliographystyle{alpha}

% Fiches de suivi.

\newcounter{sem@ine}

\setcounter{sem@ine}{1}

\newcounter{incrementb@sheures}
\newcounter{incrementb@sminutes}
\newcounter{incrementh@utheures}
\newcounter{incrementh@utminutes}

\setcounter{incrementb@sheures}{10}
\setcounter{incrementb@sminutes}{00}
\setcounter{incrementh@utheures}{12}
\setcounter{incrementh@utminutes}{30}

\newcounter{b@sheures}
\newcounter{b@sminutes}
\newcounter{h@utheures}
\newcounter{h@utminutes}

\setcounter{b@sheures}{\value{incrementb@sheures}}
\setcounter{b@sminutes}{\value{incrementb@sminutes}}
\setcounter{h@utheures}{\value{incrementh@utheures}}
\setcounter{h@utminutes}{\value{incrementh@utminutes}}

\newcounter{accumulateurheuresA}
\newcounter{accumulateurminutesA}
\newcounter{accumulateurheuresB}
\newcounter{accumulateurminutesB}

\setcounter{accumulateurheuresA}{0}
\setcounter{accumulateurminutesA}{0}
\setcounter{accumulateurheuresB}{0}
\setcounter{accumulateurminutesB}{0}

\newcounter{ratioAbas}
\newcounter{ratioBbas}
\newcounter{ratioAhaut}
\newcounter{ratioBhaut}

\newenvironment{fichesuivi}[2]
	{
	\noindent
   \begin{minipage}{\columnwidth}
	   \hrule
	   \bigskip
	   \begin{center}
	      \textbf{\WeeklyReport\hypertarget{sem:\arabic{sem@ine}}{\arabic{sem@ine}}}
	      
	      \textbf{\WeeklyReportFrom\ #1 \WeeklyReportTo\ #2}
	   \end{center}
	   \hrule
	\end{minipage}
	
	\bigskip
	}
	{
	\vspace{5\baselineskip}

	\addtocounter{sem@ine}{1}
	\addtocounter{b@sheures}{\value{incrementb@sheures}}
	\addtocounter{b@sminutes}{\value{incrementb@sminutes}}
	\ifthenelse{\value{b@sminutes}>59}
	   { % then
	   \addtocounter{b@sheures}{1}
	   \addtocounter{b@sminutes}{-60}
	   }
	   {% else
	   }
	\addtocounter{h@utheures}{\value{incrementh@utheures}}
	\addtocounter{h@utminutes}{\value{incrementh@utminutes}}
	\ifthenelse{\value{h@utminutes}>59}
	   {% then
	   \addtocounter{h@utheures}{1}
	   \addtocounter{h@utminutes}{-60}
	   }
	   {% else
	   }
	}
	
\newcommand{\tempstravailA}[2]
   {%
	\ifnum #2<0
	   \ClassError{report-rd-info}
	              {Erreur sur le temps de travail : Les minute ne peuvent pas \^etre n\'egatives}
	              {}
	\fi
	\ifnum #2>59
	   \ClassError{report-rd-info}
	              {Erreur sur le temps de travail : Les minute ne peuvent d\'epasser 59}
	              {}
	\fi
   \if\@authorAfirstname+%
   	\ClassError{report-rd-info}
		           {Erreur sur le premier bin\^ome : Il doit y avoir au moins un auteur}
		           {}
   \else
      \noindent \WeeklyAuthorWork\ \@authorAfirstname\ \textsc{\@authorAlastname}
   \fi
   : #1 h #2 m\par
	\addtocounter{accumulateurheuresA}{#1}
	\addtocounter{accumulateurminutesA}{#2}
	\ifthenelse{\value{accumulateurminutesA}>59}
	   {% then
	   \addtocounter{accumulateurheuresA}{1}
	   \addtocounter{accumulateurminutesA}{-60}
	   }
	   {% else
	   }
	\setcounter{ratioAbas}{100 * (\value{accumulateurheuresA} * 60 + \value{accumulateurminutesA}) /
	                             (\value{b@sheures}           * 60 + \value{b@sminutes})}
	\setcounter{ratioAhaut}{100 * (\value{accumulateurheuresA} * 60 + \value{accumulateurminutesA}) /
	                              (\value{h@utheures}          * 60 + \value{h@utminutes})}
	\immediate\write\summaryfile{      \string\hyperlink{sem:\arabic{sem@ine}}{\arabic{sem@ine}} &
	                                   \arabic{b@sheures} &
	                                   \ifnum\value{b@sminutes}<10 0\fi\arabic{b@sminutes} & 
	                                   \arabic{h@utheures} &
	                                   \ifnum\value{h@utminutes}<10 0\fi\arabic{h@utminutes} & 
	                                   #1 &
	                                   #2 & 
	                                   \arabic{accumulateurheuresA} & 
	                                   \ifnum\value{accumulateurminutesA}<10 0\fi\arabic{accumulateurminutesA} & 
	                                   \arabic{ratioAbas} &
	                                   \string\textcolor{r@tiomaxcolor}{(\arabic{ratioAhaut})} & }%
   \if\@authorBfirstname+%
		\immediate\write\summaryfile{        & 
		                                     & 
		                                     & 
		                                     & 
		                                    
	                                     \string\tabularnewline
	                                     \string\hline}%
	\fi
   }
   
\newcommand{\tempstravailB}[2]
   {%
   \if\@authorBfirstname+%
   	\ClassError{report-rd-info}
		           {Erreur sur le second bin\^ome : Il n'y a qu'un seul auteur}
		           {}
   \else
		\ifnum #2<0
		   \ClassError{report-rd-info}
		              {Erreur sur le temps de travail : Les minute ne peuvent pas \^etre n\'egatives}
		              {}
		\fi
		\ifnum #2>59
		   \ClassError{report-rd-info}
		              {Erreur sur le temps de travail : Les minute ne peuvent d\'epasser 59}
		              {}
		\fi
      \noindent \WeeklyAuthorWork\ \@authorBfirstname\ \textsc{\@authorBlastname} : #1 h #2 m\par
		\addtocounter{accumulateurheuresB}{#1}
		\addtocounter{accumulateurminutesB}{#2}
		\ifthenelse{\value{accumulateurminutesB}>59}
		   {% then
		   \addtocounter{accumulateurheuresB}{1}
		   \addtocounter{accumulateurminutesB}{-60}
		   }
		   {% else
		   }
		\setcounter{ratioBbas}{100 * (\value{accumulateurheuresB} * 60 + \value{accumulateurminutesB}) /
		                             (\value{b@sheures}           * 60 + \value{b@sminutes})}
		\setcounter{ratioBhaut}{100 * (\value{accumulateurheuresB} * 60 + \value{accumulateurminutesB}) /
		                              (\value{h@utheures}          * 60 + \value{h@utminutes})}
		\immediate\write\summaryfile{      #1 & 
		                                   #2 & 
		                                   \arabic{accumulateurheuresB} & 
		                                   \ifnum\value{accumulateurminutesB}<10 0\fi\arabic{accumulateurminutesB} & 
		                                   \arabic{ratioBbas} &
		                                   \string\textcolor{r@tiomaxcolor}{(\arabic{ratioBhaut})}
	                                      \string\tabularnewline
	                                      \string\hline}%
   \fi
   }
   
\newenvironment{travaileffectue}
	{\noindent\textbf{\WeeklyWorkDone.}\par}
	{}
	
\newenvironment{travailnoneffectue}
	{\noindent\textbf{\WeeklyWorkNotDone.}\par}
	{}
	
\newenvironment{echange}
	{\noindent\textbf{\WeeklyExchange.}\par}
	{}
	
\newenvironment{planification}
	{\noindent\textbf{\WeeklyNextPlanning.}\par}
	{}

% Recapitulatif du travail hebdomadaire
\newwrite\summaryfile
\immediate\openout\summaryfile=report-rd-summary.tex

\newcommand{\printweeksummary}
   {%
	\immediate\closeout\summaryfile
	\makeatletter
	\begin{table*}
	   \centering
	   \begin{tabular}{|c||r@{ : }r|r@{ : }r||r@{ : }r|r@{ : }r|rr||r@{ : }r|r@{ : }r|rr|}
	      \cline{2-17}
	      \multicolumn{1}{c|}{} & 
	      \multicolumn{4}{c||}{\if@inEnglish Planned\else Temps\fi} &
	      \multicolumn{6}{c||}{\@authorAfirstname} &
	      \multicolumn{6}
	                  {c|}
	                  {%
	                  \if\@authorBfirstname+%
	                  \else
	                     \@authorBfirstname
	                  \fi
	                  }
	      \tabularnewline
	      \multicolumn{1}{c|}{} &
	      \multicolumn{4}{c||}{\if@inEnglish Time\else prévu\fi} &
	      \multicolumn{6}{c||}{\textsc{\@authorAlastname}} &
	      \multicolumn{6}
	                  {c|}
	                  {%
	                  \if\@authorBlastname+%
	                  \else
	                     \textsc{\@authorBlastname}%
	                  \fi
	                  }
	      \tabularnewline
	      \cline{2-17}
	      \multicolumn{1}{c|}{} &
	      \multicolumn{2}{c|}{\if@inEnglish low\else bas\fi} &
	      \multicolumn{2}{c||}{\if@inEnglish high\else haut\fi} &
	      \multicolumn{2}{c|}{\if@inEnglish weekly\else hebdo.\fi} &
	      \multicolumn{2}{c|}{$\Sigma$} &
	      \multicolumn{2}{c||}{\multirow{2}{*}{\%}} &
	      \multicolumn{2}{c|}{\if@inEnglish weekly\else hebdo.\fi} &
	      \multicolumn{2}{c|}{$\Sigma$} &
	      \multicolumn{2}{c|}{\multirow{2}{*}{\%}}
	      \tabularnewline
	      \cline{1-9}\cline{12-15}
	      \if@inEnglish Week\else Semaine\fi & h & m & h & m & h & m & h & m & & & h & m & h & m & & 
	                                   \tabularnewline
	      \hline
	                                   \hline

		   \input{report-rd-summary.tex}
   
	   \end{tabular}
		\if@inEnglish
		   \caption{Advance of the project with respect to the planned minimal theoretical time (respectively, a high involvement)}
		\else
		   \caption{Avancement du projet par rapport au temps de travail théorique minimal (respectivement haut)}
	   \fi
	   \label{tab:AvancementProjet}
	\end{table*}
	\makeatother
	
	\if@inEnglish
		Table~\ref{tab:AvancementProjet} summarises the rate at which the projects advances. Let us recall that the ``planned minimal theoretical time'' equals the time indicated in the programme plus 20\% of additional time corresponding to personal work, i.e., outside the timetable. The high limit corresponds to 50\% of aditional personal work.
	\else
		Le tableau~\ref{tab:AvancementProjet} récapitule le taux d'avancement du projet. Rappelons que le temps de travail théorique \emph{minimal} correspond au temps indiqué sur la maquette pédagogique auquel on ajoute un strict minimum de 20~\% correspondant au travail personnel hors emploi du temps. La partie << haute >> de la fourchette correspond à 50~\% de temps supplémentaire au titre du travail personnel.
   \fi
   }


\endinput


%%% Local Variables:
%%% mode: latex
%%% TeX-master: t
%%% End:
