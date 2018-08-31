package ca.corefacility.bioinformatics.irida.plugins;

import java.awt.Color;
import java.util.Optional;
import java.util.UUID;

import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

import ca.corefacility.bioinformatics.irida.model.workflow.analysis.type.AnalysisType;
import ca.corefacility.bioinformatics.irida.plugins.IridaPlugin;

/**
 * An example {@link IridaPlugin} implementation
 */
public class ExamplePlugin extends Plugin {

	public ExamplePlugin(PluginWrapper wrapper) {
		super(wrapper);
	}

	@Extension
	public static class PluginInfo implements IridaPlugin {

		/***************************************************************************************
		 * Required methods
		 * 
		 * These methods are required to be overridden when implementing a pipeline as a plugin.
		 ***************************************************************************************/

		/**
		 * Gets the particular {@link AnalysisType} object for this workflow.
		 * This should correspond to the <strong>analysisType</strong> entry in the <strong>irida_workflow.xml</strong> file.
		 * 
		 * <pre>{@code <analysisType>READ_INFO</analysisType>}</pre>
		 */
		@Override
		public AnalysisType getAnalysisType() {
			return new AnalysisType("READ_INFO");
		}

		/**
		 * Gets the particular workflow id.
		 * This should correspond to the <strong>id</strong> entry in the <strong>irida_workflow.xml</strong> file.
		 * 
		 * <pre>{@code <id>79d90ca8-00ae-441b-b5c7-193c9e85a968</id>}</pre>
		 */
		@Override
		public UUID getDefaultWorkflowUUID() {
			return UUID.fromString("79d90ca8-00ae-441b-b5c7-193c9e85a968");
		}
		
		/*********************************************************************************************************************
		 * Optional methods.
		 * 
		 * These methods are not required to be overridden but can be used to adjust the appearance/behaviour of the pipeline.
		 *********************************************************************************************************************/
		
		/**
		 * Defines the background color in the IRIDA UI corresponding to this pipeline.
		 */
		@Override
		public Optional<Color> getBackgroundColor() {
			return Optional.of(Color.decode("#dd1c77"));
		}

		/**
		 * Defines the text color in the IRIDA UI corresponding to this pipeline.
		 */
		@Override
		public Optional<Color> getTextColor() {
			return Optional.of(Color.BLACK);
		}
	}
}
