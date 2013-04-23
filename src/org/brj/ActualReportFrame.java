package org.brj;

import java.awt.BorderLayout;
import java.io.*;
import org.bjvdb.*;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.InputStream;
import java.sql.ResultSet;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import com.crystaldecisions.ReportViewer.ReportViewerBean;
import com.crystaldecisions.sdk.occa.report.application.OpenReportOptions;
import com.crystaldecisions.sdk.occa.report.application.PrintOutputController;
import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.application.ReportSource;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;
import com.crystaldecisions.ReportViewer.ReportViewerBean;
import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.exportoptions.ExportOptions;
import com.crystaldecisions.sdk.occa.report.exportoptions.PDFExportFormatOptions;
import com.crystaldecisions.sdk.occa.report.exportoptions.ReportExportFormat;

public class ActualReportFrame extends JFrame{
	 
	public static final String FRAME_TITLE = "Sample Report Viewer";

	    /** The report viewer bean instance. */ 
	    private final ReportViewerBean reportViewer;

	    /** The ReportClientDocument instance being used. 
	     *  Set by loadReport(). */
	    private ReportClientDocument reportClientDocument;
	    public ActualReportFrame()
	    {
	    	setTitle (FRAME_TITLE);
	        
	        this.reportViewer = new ReportViewerBean();
	        reportViewer.init ();

	        // A menu bar can be added here if desired
	        
	        // Handle closing of the viewer.
	        addWindowListener (new WindowAdapter ()
	        {
	            public void windowClosing (WindowEvent e)
	            {
	                closeViewer ();
	            }
	        });
	        
	        getContentPane ().add (reportViewer, BorderLayout.CENTER);

	        // Set to some default size
	        Insets insets = getInsets ();
	        setSize (insets.left + 700 + insets.right, insets.top + 500 + insets.bottom);

	        // Show in a sensible location for the platform.
	        setLocationByPlatform (true);
	    }
	    
	    /**
	     * Create a new instance of this viewer class and show it.
	     *
	     * @return the new instance of this class that was created.
	     */
	    static ActualReportFrame showViewerFrame () 
	    {
	        ActualReportFrame viewerFrame = new ActualReportFrame();
	        viewerFrame.setVisible (true);
	        
	        // Start the viewer
	        viewerFrame.reportViewer.start ();
	        
	        return viewerFrame;
	    }
	    public static void showViewer () 
	    {
	        ActualReportFrame viewerFrame = showViewerFrame ();
	        boolean success = viewerFrame.showReport ();
	        if (!success) {
	            viewerFrame.closeViewer ();
	        }
	    }
	    private void closeViewer ()
	    {
	        if (reportViewer != null)
	        {
	            reportViewer.stop ();
	            reportViewer.destroy ();
	        }
	        
	        removeAll ();
	        dispose ();
	    }
	    private boolean showReport () 
	    {
	        try
	        {
	            loadReport ();

	            if (reportClientDocument != null) {
	                setDatabaseLogon ();
	                setParameterFieldValues ();
	                setReportSource ();
	                
	                return true;
	            }
	        }
	        catch (ReportSDKException e)
	        {
	            String localizedMessage = e.getLocalizedMessage ();
	            int errorCode = e.errorCode ();
	            
	            String title = "Problem showing report";
	            String message = localizedMessage + "\nError code: " + errorCode;
	            JOptionPane.showMessageDialog (ActualReportFrame.this, message, title, JOptionPane.WARNING_MESSAGE);
	        }
	        return false;
	    } 
	    private void loadReport () throws ReportSDKException
	    {
	    	 reportClientDocument = new ReportClientDocument ();
	    	 AllDBMethods a=new AllDBMethods();
	    	 
	    	 //File f=new File("./src/sample.rpt");
	    	// reportClientDocument.open ("sample.rpt",0); 
	    	// PrintOutputController p=reportClientDocument.getPrintOutputController();
	    	//InputStream i= p.export(ReportExportFormat.PDF);
	    	 //reportClientDocument.open("seedReport.rpt",OpenReportOptions._openAsReadOnly);
	    	// ReportClientDocument reportClientDoc = new ReportClientDocument();
			reportClientDocument.open("sample.rpt", 0);
	    	ResultSet rs=a.retrieveDB("seeds", "seedname");
	    	String tableAlias = reportClientDocument.getDatabaseController().getDatabase().getTables().getTable(0).getAlias();
	    	 reportClientDocument.getDatabaseController ().setDataSource(rs,tableAlias,"seeds");
	    	// reportClientDocument.getDatabaseController().setDataSource(rs, tableAlias, tableAlias); 
	    	 reportViewer.setReportSource(reportClientDocument.getReportSource());
	 		reportViewer.init();
	 		reportViewer.start();

				
				//Launch JFrame that contains the report viewer. 
	    	 
	    	 
	    	 
	    }
	    private void setDatabaseLogon () throws ReportSDKException
	    {
	        // TODO Set up database logon here to have the report log onto the
	    	// data sources defined in the report automatically, without prompting the
	    	// user.  For more information about this feature, refer to the documentation.
	    	// For example: 
	    	//  CRJavaHelper.logonDataSource (reportClientDocument, "username", "password");
	    	// will log onto the data sources defined in the report with username "username"
	    	// and password "password". 
	    }
	    private void setParameterFieldValues () throws ReportSDKException
	    {
	    	// TODO Populate the parameter fields in the report document here, to
	    	// view the report without prompting the user for parameter values.  For
	    	// more information about this feature, refer to the documentation.
	    	// For example:
	        //  CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "subreportName", 
	    	//			"parameterName", newValue);
	    	// will populate the discrete parameter "parameterName" under the subreport
	    	// "subreportName" with a new value (newValue).  To set a parameter in the main
	    	// report, use an empty string ("") instead of "subreportName".
	    }
	    private void setReportSource ()
	    {
	      
	    }
	    public static InputStream exportPDF (ReportClientDocument clientDoc) throws ReportSDKException
	    {
	        // PDF export allows page range export. The following routine ensures
	        // that the requested page range is valid
	        PDFExportFormatOptions pdfOptions = new PDFExportFormatOptions ();
	        ExportOptions exportOptions = new ExportOptions ();
	        exportOptions.setExportFormatType (ReportExportFormat.PDF);
	        exportOptions.setFormatOptions (pdfOptions);

	        // Export the report using the export options.
	        return clientDoc.getPrintOutputController ().export (exportOptions);
	    }


}