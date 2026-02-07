USE [BloodManagementDB]
GO

/****** Object:  Table [dbo].[Hospitals]    Script Date: 2/7/2026 2:40:29 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Hospitals](
	[HospitalID] [int] IDENTITY(1,1) NOT NULL,
	[HospitalName] [nvarchar](100) NOT NULL,
	[ContactNumber] [varchar](20) NOT NULL,
	[Location] [nvarchar](255) NOT NULL,
	[Email] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[HospitalID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


