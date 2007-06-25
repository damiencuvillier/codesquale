using System;
using System.Collections.Generic;
using System.Text;

namespace ImmoGestX.DAL
{
    public class NHibernateInit
    {
        #region Design pattern : << Singleton >>

        // Static members are lazily initialized. 
        // .NET guarantees thread safety for static initialization 
        private static readonly NHibernateInit instance = new NHibernateInit();
        private NHibernateInit() { }
        public static NHibernateInit Instance { get { return instance; } }

        #endregion

        private static NHibernate.ISessionFactory _sessionFactory;
        private NHibernate.Cfg.Configuration cfg;
        private string database;
        private string connexionString;


        
        public NHibernate.ISessionFactory sessionFactory
        {
            get { return _sessionFactory; }
            set { _sessionFactory = value; }
        }


        public void configureNHibernate(string bdd, string conx)
        {

            try
            {
                database = bdd;
                connexionString = conx;

                System.Console.Out.WriteLine("Configuration de NHibernate...\n");

                // Active l'inscription des informations de d�bogages sur les op�rations de NHibernate
                log4net.Config.XmlConfigurator.Configure();

                // Cr�e l'objet qui contiendras les informations de configuration
                // et assigne les informations pour acc�der � la base de donn�es
                cfg = new NHibernate.Cfg.Configuration();
                cfg.SetProperty(NHibernate.Cfg.Environment.ConnectionProvider, "NHibernate.Connection.DriverConnectionProvider");

                System.Console.Out.WriteLine("Utilise la base de donn�es: <" + database
                 + ">\nCha�neDeConnexion: <" + connexionString + ">\n");


                if ("Access" == database)
                {
                    cfg.SetProperty(NHibernate.Cfg.Environment.Dialect, "NHibernate.JetDriver.JetDialect, NHibernate.JetDriver");
                    cfg.SetProperty(NHibernate.Cfg.Environment.ConnectionDriver, "NHibernate.JetDriver.JetDriver, NHibernate.JetDriver");
                }
                else if ("MSSQL" == database)
                {
                    cfg.SetProperty(NHibernate.Cfg.Environment.Dialect, "NHibernate.Dialect.MsSql2000Dialect");
                    cfg.SetProperty(NHibernate.Cfg.Environment.ConnectionDriver, "NHibernate.Driver.SqlClientDriver");
                }
                else if ("MySQL" == database)
                {
                    cfg.SetProperty(NHibernate.Cfg.Environment.Dialect, "NHibernate.Dialect.MySQLDialect");
                    cfg.SetProperty(NHibernate.Cfg.Environment.ConnectionDriver, "NHibernate.Driver.MySqlDataDriver");
                }
                else
                    throw new System.InvalidOperationException("La base de donn�es '" + database + "' est inconnue.");

                cfg.SetProperty(NHibernate.Cfg.Environment.ConnectionString, connexionString);

               }
                catch (System.Exception ex)
                {
                    System.Console.Out.WriteLine("\n\n" + ex.ToString() + "\n\n");
                }

        
        }


        public void initDataBaseFromEntities()
        {
                // Utilise NHibernate.Mapping.Attributes pour cr�er les informations sur les entit�s
                System.IO.MemoryStream flux = new System.IO.MemoryStream(); // Contenant des informations
                System.IO.MemoryStream flux2 = new System.IO.MemoryStream(); // Contenant des informations
                NHibernate.Mapping.Attributes.HbmSerializer.Default.Validate = true; // Active la validation (optionnel)
                // Demande � NHibernate d'utiliser les champs et non les propri�t�s (dans les entit�s)
                NHibernate.Mapping.Attributes.HbmSerializer.Default.HbmDefaultAccess = "field.camelcase-underscore";
                // R�cup�re les informations � partir de cette assembl�e (peut aussi �tre fait classe par classe)
                System.Console.Out.WriteLine("NHibernate.Mapping.Attributes.HbmSerializer.Default.Serialize()...\n");
                NHibernate.Mapping.Attributes.HbmSerializer.Default.Serialize(flux, System.Reflection.Assembly.LoadFile(System.Windows.Forms.Application.StartupPath +@"\ImmoGestX.BLL.dll")); //.GetAssembly(typeof(ImmoGestX.BLL.Proprietaire))); //.GetExecutingAssembly());
                NHibernate.Mapping.Attributes.HbmSerializer.Default.Serialize(flux2, System.Reflection.Assembly.LoadFile(System.Windows.Forms.Application.StartupPath + @"\ImmoGestX.Common.dll")); //.GetAssembly(typeof(ImmoGestX.BLL.Proprietaire))); //.GetExecutingAssembly());
                //NHibernate.Mapping.Attributes.HbmSerializer.Default.Serialize(flux, System.Reflection.Assembly.GetAssembly(typeof(ImmoGestX.BLL.Location.Paiement)));
                flux.Position = 0;
                cfg.AddInputStream(flux); // Envoi les informations de Mappage � la Configuration de NHibernate
                flux.Close();
                flux2.Position = 0;
                cfg.AddInputStream(flux2);
                flux2.Close();

                // Construit la "fabrique de sessions"
                System.Console.Out.WriteLine("\n\nsessionFact = cfg.BuildSessionFactory();\n\n");
                _sessionFactory = cfg.BuildSessionFactory();
         }
        public void createDataBaseFromEntities()
        {
                // Cr�� le(s) table(s) dans la base de donn�es pour les entit�s
                System.Console.Out.WriteLine("new NHibernate.Tool.hbm2ddl.SchemaExport(cfg).Create()...");
                new NHibernate.Tool.hbm2ddl.SchemaExport(cfg).Create(true, true);
        }
        public void exportSchemaFromEntities(string file)
        {
            new NHibernate.Tool.hbm2ddl.SchemaExport(cfg).SetOutputFile(file).Create(true,true);
        }
    }
}
