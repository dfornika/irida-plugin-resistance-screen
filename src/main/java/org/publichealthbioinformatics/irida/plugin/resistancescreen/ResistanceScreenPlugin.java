package org.publichealthbioinformatics.irida.plugin.resistancescreen;

import java.awt.Color;
import java.util.Optional;
import java.util.UUID;

import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

import ca.corefacility.bioinformatics.irida.model.sample.Sample;
import ca.corefacility.bioinformatics.irida.plugins.IridaPlugin;
import ca.corefacility.bioinformatics.irida.plugins.IridaPluginException;
import ca.corefacility.bioinformatics.irida.model.workflow.analysis.type.AnalysisType;
import ca.corefacility.bioinformatics.irida.pipeline.results.updater.AnalysisSampleUpdater;
import ca.corefacility.bioinformatics.irida.service.sample.MetadataTemplateService;
import ca.corefacility.bioinformatics.irida.service.sample.SampleService;
import ca.corefacility.bioinformatics.irida.service.workflow.IridaWorkflowsService;

/**
 * An example {@link IridaPlugin} implementation which will extract some
 * information from the sequencing reads.
 */
public class ResistanceScreenPlugin extends Plugin {

	/**
	 * The {@link AnalysisType} used by this plugin. This wraps around a string and
	 * is used to store the type of the analysis pipeline (which should be unique
	 * for each pipeline).
	 */
	public static final AnalysisType RESISTANCE_SCREEN = new AnalysisType("RESISTANCE_SCREEN");

	public ResistanceScreenPlugin(PluginWrapper wrapper) {
		super(wrapper);
	}

	/**
	 * This class defines information about this particular plugin. On start-up,
	 * IRIDA will make use of the information provided by this class to integrate
	 * the plugin pipeline.
	 */
	@Extension
	public static class PluginInfo implements IridaPlugin {

		/*******************************************************************************
		 * Required methods
		 * 
		 * These methods are required to be overridden when implementing a pipeline as a
		 * plugin.
		 *******************************************************************************/

		/**
		 * Gets the particular {@link AnalysisType} object for this workflow. This
		 * should correspond to the <strong>analysisType</strong> entry in the
		 * <strong>irida_workflow.xml</strong> file.
		 * 
		 * <pre>
		 * {@code <analysisType>READ_INFO</analysisType>}
		 * </pre>
		 * 
		 * @return The particular {@link AnalysisType} for this pipeline.
		 */
		@Override
		public AnalysisType getAnalysisType() {
			return RESISTANCE_SCREEN;
		}

		/**
		 * Gets the particular workflow id. This should correspond to the
		 * <strong>id</strong> entry in the <strong>irida_workflow.xml</strong> file.
		 * 
		 * <pre>
		 * {@code <id>3b697fef-2811-45cf-8634-b89f6cfdb7cb</id>}
		 * </pre>
		 * 
		 * @return A {@link UUID} defining the id of this pipeline.
		 */
		@Override
		public UUID getDefaultWorkflowUUID() {
			return UUID.fromString("3b697fef-2811-45cf-8634-b89f6cfdb7cb");
		}

		/*******************************************************************************
		 * Optional methods
		 * 
		 * These methods are not required to be overridden but can be used to adjust the
		 * appearance/behavior of the pipeline.
		 *******************************************************************************/

		/**
		 * Defines the background color in the IRIDA UI corresponding to this pipeline.
		 * 
		 * @return An {@link Optional} color for the background of the IRIDA UI for this
		 *         pipeline.
		 */
		@Override
		public Optional<Color> getBackgroundColor() {
			return Optional.of(Color.decode("#07deed"));
		}

		/**
		 * Defines the text color in the IRIDA UI corresponding to this pipeline.
		 * 
		 * @return An {@link Optional} color for the text of the IRIDA UI for this
		 *         pipeline.
		 */
		@Override
		public Optional<Color> getTextColor() {
			return Optional.of(Color.BLACK);
		}

		/**
		 * Builds a {@link Optional} {@link AnalysisSampleUpdater} used to update
		 * metadata from the pipeline results.
		 * 
		 * @param metadataTemplateService An IRIDA service to manipulate and save
		 *                                metadata templates.
		 * @param sampleService           An IRIDA service to manipulate and save
		 *                                information associated with a {@link Sample}.
		 * @param iridaWorkflowsService   An IRIDA service for getting information about
		 *                                the workflow.
		 * 
		 * @return An {@link Optional} {@link AnalysisSampleUpdater} used to update
		 *         metadata from the pipeline results.
		 */
		@Override
		public Optional<AnalysisSampleUpdater> getUpdater(MetadataTemplateService metadataTemplateService,
				SampleService sampleService, IridaWorkflowsService iridaWorkflowsService) throws IridaPluginException {
			return Optional.of(new ResistanceScreenPluginUpdater(metadataTemplateService, sampleService, iridaWorkflowsService));
		}
	}
}
